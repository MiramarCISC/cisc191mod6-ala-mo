package edu.sdccd.cisc191;

import java.util.*;
import java.util.stream.*;
/*
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set; */

public class GameServerAnalytics {

    public static List<String> findTopNUsernamesByRating(Collection<PlayerAccount> players, int n) {
        // finding the top "n" usernames when sorted by rating
        // using a stream pipeline
        return players.stream() // creates the stream for players
                // compares the ratings of the players by highest rating first
                .sorted(Comparator.comparingInt(PlayerAccount::rating).reversed())
                .limit(n)
                // extracts usernames
                .map(PlayerAccount::username)
                .toList();
    }

    public static Map<String, Double> averageRatingByRegion(Collection<PlayerAccount> players) {
        // finding the average rating by region
        // using groupingBy + averagingInt
        return players.stream()
                .collect(Collectors.groupingBy(
                        // groups the players into regions
                        PlayerAccount::region,
                        // averages rating by the accounts already grouped into their respective regions
                        Collectors.averagingInt(PlayerAccount::rating))
                );
    }

    public static Set<String> findDuplicateUsernames(Collection<PlayerAccount> players) {
        // finding duplicate usernames
        // using collections and/or streams
        Set<String> seen = new HashSet<>();
        return players.stream()
                .map(PlayerAccount::username)
                // if seen.add returns false, that means that it's detected a duplicate
                .filter(username -> !seen.add(username))
                .collect(Collectors.toSet());
    }

    public static Map<String, List<String>> groupUsernamesByTier(Collection<PlayerAccount> players) {
        // grouping usernames / players by tiers
        // using groupingBy and mapping
        return players.stream()
                .collect(Collectors.groupingBy(
                    // groups usernames into tiers using tierFor() method
                    GameServerAnalytics::tierFor,
                    // transforms values inside grouping
                    Collectors.mapping(PlayerAccount::username, Collectors.toList()))
                );
    }

    public static Map<String, List<String>> buildRecentMatchSummariesByPlayer(Collection<MatchRecord> matches) {
        // building recent match summaries by player
        // using a Map + collection logic or a stream-based approach
        Map<String, List<String>> result = new HashMap<>();

        for (MatchRecord match : matches) {
            String summary = match.summary();

            // initializing the result list for both players if they don't have one
            result.computeIfAbsent(match.playerOne().username(), k -> new ArrayList<>())
                    .add(summary);

            result.computeIfAbsent(match.playerTwo().username(), k -> new ArrayList<>())
                    .add(summary);
        }

        return result;
    }

    public static <T> T pickHigherRated(T first, T second, Comparator<T> comparator) {
        // choosing the higher rating using the comparator
        // checks if first is greater than second, then returns the higher one when found
        return comparator.compare(first, second) >=  0 ? first : second;
    }

    public static String tierFor(PlayerAccount player) {
        if (player.rating() < 1000) return "Bronze";
        if (player.rating() < 1400) return "Silver";
        return "Gold";
    }
}