package com.education.ztu.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Team<T extends Participant> {
    private String name;
    private List<T> participants = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public Team(Team<T> other) {
        this.name = other.name;
        this.participants = new ArrayList<>();
        for (T participant : other.participants) {
            this.participants.add((T) participant.clone());
        }
    }

    public static <T extends Participant> Team<T> deepClone(Team<T> original) {
        return new Team<>(original);
    }

    public void addNewParticipant(T participant) {
        participants.add(participant);
        System.out.println("To the team " + name + " was added participant " + participant.getName());
    }

    public void playWith(Team<T> team) {
        String winnerName;
        Random random = new Random();
        int i = random.nextInt(2);
        if (i == 0) {
            winnerName = this.name;
        } else {
            winnerName = team.name;
        }
        System.out.println("The team " + winnerName + " is winner!");
    }

    public String getName() {
        return name;
    }

    public List<T> getParticipants() {
        return participants;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParticipants(List<T> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", participants=" + participants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team<?> team = (Team<?>) o;
        return Objects.equals(name, team.name) && Objects.equals(participants, team.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, participants);
    }
}