package com.nitok.netcrackercourse.week1.bones;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BonesGame {
    List<BonesPlayer> players = new ArrayList<>();
    private final int bonesCount;

    public BonesGame(int playerCount, int bonesCount) {
        if (playerCount <= 1) {
            throw new IllegalArgumentException("Колво игроков должно быть больше 1.");
        }
        if (bonesCount <= 0) {
            throw new IllegalArgumentException("Колво костей должно быть положительным.");
        }
        this.bonesCount = bonesCount;
        for (int i = 0; i < playerCount - 1; i++) {
            players.add(new BonesPlayer("игрок " + (i + 1)));
        }
        players.add(new ComputerBonesPlayer());
    }

    private BonesPlayer makeStep(int round, int startnum) {
        System.out.println("Начало " + round + " раунда");
        BonesPlayer bestPlayer = null;
        int bestSum = 0;
        int i = 0; //счетчик обхода игроков
        int j = startnum; //индекс текущего игрока
        while (i < players.size()) {
            BonesPlayer player = players.get(j);
            System.out.println(player.getName() + ", кидайте кости");
            int sum = player.takeBones(bonesCount);
            System.out.println(player.getName() + " выкинул " + sum);
            if (bestPlayer == null || sum > bestSum) {
                bestPlayer = player;
                bestSum = sum;
            }
            i++;
            j++;
            if (j == players.size()) {
                j = 0;
            }
        }
        if(bestPlayer == null){
            throw new IllegalStateException("все сломалось");
        }
        bestPlayer.incWin();
        System.out.println("В раунде " + round + " победил " + bestPlayer.getName());
        return bestPlayer;
    }

    public void start() {
        int round = 1;
        int startPlayerId = 0;
        while (true) {
            BonesPlayer winner = makeStep(round, startPlayerId);
            if (isTotalWon(winner)) {
                System.out.println("победитель " + winner.getName());
                return;
            }
            startPlayerId = players.indexOf(winner);
            round++;
        }
    }

    private boolean isTotalWon(BonesPlayer player) {
        return player.getWin() >= 7;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите кол-во игроков");
        int N = scanner.nextInt();

        System.out.println("Введите кол-во костей");
        int K = scanner.nextInt();

        BonesGame bonesGame = new BonesGame(N, K);
        bonesGame.start();

    }
}
