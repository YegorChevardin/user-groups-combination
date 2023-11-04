package com.reactdev.projects.usercombinations.service.services.impl;

import com.reactdev.projects.usercombinations.repository.entities.UserCombinationEntity;
import com.reactdev.projects.usercombinations.repository.entities.UserEntity;
import com.reactdev.projects.usercombinations.repository.repositories.UserCombinationsRepository;
import com.reactdev.projects.usercombinations.repository.repositories.UserRepository;
import com.reactdev.projects.usercombinations.service.convertors.ReverseEntityDtoConvertor;
import com.reactdev.projects.usercombinations.service.services.UserCombinationsService;
import com.reactdev.projects.usercombinations.web.dto.User;
import com.reactdev.projects.usercombinations.web.dto.UserCombination;
import com.reactdev.projects.usercombinations.web.exceptions.impl.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserCombinationsServiceImpl implements UserCombinationsService {
    private static final String FIRST_TEAM_NAME = "Orange team";
    private static final String SECOND_TEAM_NAME = "Purple team";

    private final UserCombinationsRepository userCombinationsRepository;
    private final UserRepository userRepository;
    private final ReverseEntityDtoConvertor<UserCombinationEntity, UserCombination>
            userCombinationEntityDtoConvertor;
    private final ReverseEntityDtoConvertor<UserEntity, User> userReverseEntityDtoConvertor;

    @Override
    public List<UserCombination> generateNewCombinations(List<User> users) {
        List<UserCombination> userCombination =
                userCombinationEntityDtoConvertor.convertEntityToDto(userCombinationsRepository.findAll());

        List<UserEntity> allUsersFromBD = userRepository.findAllByIdIn(getIds(users));
        List<User> allUsersDTO = userReverseEntityDtoConvertor.convertEntityToDto(allUsersFromBD);

        List<Long> ids = compareIds(getIds(users), getIds(allUsersDTO));

        if (!ids.isEmpty()) {
            throw new DataNotFoundException(
                    "User(s) with id(s)" + ids + " does not exist in DB");
        }

        List<User> firstCommand = new ArrayList<>();
        List<User> secondCommand = new ArrayList<>();
        for (User user : allUsersDTO) {
            if (user.getTeam().getName().equals(FIRST_TEAM_NAME)) {
                firstCommand.add(user);
            } else if (user.getTeam().getName().equals(SECOND_TEAM_NAME)) {
                secondCommand.add(user);
            }
        }

        if (firstCommand.isEmpty() || secondCommand.isEmpty()) {
            throw new DataNotFoundException("Cannot continue action, because teams are empty");
        }

        userCombinationsRepository.deleteAll();
        List<UserCombinationEntity> userCombinationEntities =
                userCombinationsRepository.saveAll(
                        userCombinationEntityDtoConvertor.dtoToEntity(
                                generateUniqueUserCombinations(userCombination, firstCommand, secondCommand)));

        return userCombinationEntityDtoConvertor.convertEntityToDto(userCombinationEntities);
    }

    private List<UserCombination> generateUniqueUserCombinations(
            List<UserCombination> oldCombinations, List<User> firstCommand, List<User> secondCommand) {
        List<User> biggerCommand;
        firstCommand = new ArrayList<>(firstCommand);
        secondCommand = new ArrayList<>(secondCommand);

        Collections.shuffle(firstCommand);
        Collections.shuffle(secondCommand);

        List<UserCombination> newCombinations;

        if (firstCommand.size() < secondCommand.size()) {
            biggerCommand = secondCommand;
            newCombinations = performAction(firstCommand, secondCommand);
        } else {
            biggerCommand = firstCommand;
            newCombinations = performAction(secondCommand, firstCommand);
        }

        Set<UserCombination> set1 = new HashSet<>(newCombinations);
        Set<UserCombination> set2 = new HashSet<>(oldCombinations);

        if (!compareTeams(set1, set2, new HashSet<>(biggerCommand))) {
            return newCombinations;
        } else {
            return generateUniqueUserCombinations(oldCombinations, firstCommand, secondCommand);
        }
    }

    private boolean compareTeams(
            Set<UserCombination> newCombinations,
            Set<UserCombination> oldCombinations,
            Set<User> biggerTeamUsers) {
        for (UserCombination element : newCombinations) {
            if (oldCombinations.contains(element)) {
                return true;
            }
        }

        List<User> biggerTeamUsersFromOldCombinations =
                extractSecondUsersFromCombinations(oldCombinations);
        Set<User> biggerTeamUsersFromNewCombinations =
                new HashSet<>(extractSecondUsersFromCombinations(newCombinations));

        Set<User> userDifference = new HashSet<>(biggerTeamUsers);
        biggerTeamUsersFromOldCombinations.forEach(userDifference::remove);
        userDifference.addAll(biggerTeamUsersFromOldCombinations);

        if (userDifference.size() > biggerTeamUsersFromNewCombinations.size()) {
            return !userDifference.containsAll(biggerTeamUsersFromNewCombinations);
        }
        return !biggerTeamUsersFromNewCombinations.containsAll(userDifference);
    }

    private List<UserCombination> performAction(List<User> iterable, List<User> partners) {
        List<UserCombination> combinations = new ArrayList<>();

        for (User currentFirstUser : iterable) {
            UserCombination userCombination = new UserCombination();
            userCombination.setFirstUser(currentFirstUser);
            userCombination.setSecondUser(partners.get(iterable.indexOf(currentFirstUser)));
            combinations.add(userCombination);
        }

        return combinations;
    }

    private List<User> extractSecondUsersFromCombinations(Set<UserCombination> combinations) {
        List<User> result = new ArrayList<>();

        for (UserCombination combination : combinations) {
            result.add(combination.getSecondUser());
        }

        return result;
    }

    private List<Long> getIds(List<User> users) {
        return users.stream().map(User::getId).toList();
    }

    private List<Long> compareIds(List<Long> idsFromFront, List<Long> idsFromDB) {
        Set<Long> setIdsFromFront = new HashSet<>(idsFromFront);
        Set<Long> setIdsFromDB = new HashSet<>(idsFromDB);
        List<Long> ids = new ArrayList<>();

        for (Long id : setIdsFromFront) {
            if (!setIdsFromDB.contains(id)) {
                ids.add(id);
            }
        }
        return ids;
    }
}
