package com.game;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Level1 {

	static Scanner sc = new Scanner(System.in);
	int[][] snakeInx;
	int[][] ladderInx;
	static Map<String, Integer> playersList;

	int rotateDice() {
		int n = (int) (Math.random() * 6) + 1;
		return n;
	}

	public static void main(String[] args) {

		Level1 gL1 = new Level1();

		System.out.println("Enter Board size: ");
		int size = sc.nextInt();
		sc.nextLine();

		System.out.println("Enter number of snakes:");
		int sC = sc.nextInt();
		gL1.getSnakeIndex(sC);

		System.out.println("Enter number of ladders:");
		int lC = sc.nextInt();
		gL1.getLadderIndex(lC);

		System.out.println("Enter number of players:");
		int pC = sc.nextInt();
		sc.nextLine();
		gL1.getPlayerList(pC);

		gL1.getResult(size);

	}

	private void getPlayerList(int pC) {

		playersList = new LinkedHashMap<>();

		for (int i = 0; i < pC; i++) {
			System.out.println("Enter player name: ");

			playersList.put(sc.nextLine(), 0);

		}
	}

	private void getResult(int n) {

		int count = 0;

		Set<String> nameL = playersList.keySet();
		outerLoop: while (count < 100) {
			innerLoop: for (String name : nameL) {
				int position = playersList.get(name);
				int diceV = rotateDice();
				int curPos = position + diceV;
				for (int[] arr : snakeInx) {
					if (curPos == arr[1]) {
						System.out.println(name + " rolled at " + diceV + " and caught by snake moved from "
								+ (position + diceV) + " to " + arr[0] + ".");
						playersList.put(name, arr[0]);
						count++;
						continue innerLoop;
					}
				}
				for (int[] arr : ladderInx) {
					if (curPos == arr[0]) {
						System.out.println(name + " rolled at " + diceV + " and find ladder moved from "
								+ (position + diceV) + " to " + arr[1] + ".");
						playersList.put(name, arr[1]);
						count++;
						continue innerLoop;
					}
				}

				if (curPos == n) {
					System.out
					.println(name + " rolled at " + diceV + " and moved from " + position + " to " + curPos + ".");

					System.out.println(name + " wins in " + count + " th time dice Rotation");
					break outerLoop;
				} else if (curPos > 100) {
					curPos = position;
				}
				playersList.put(name, curPos);
				System.out
						.println(name + " rolled at " + diceV + " and moved from " + position + " to " + curPos + ".");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			count++;

		}
	}

	private void getSnakeIndex(int n) {

		snakeInx = new int[n][2];

		for (int i = 0; i < n; i++) {
			System.out.println("Enter index of snake: tail head");

			int tail = sc.nextInt();
			int head = sc.nextInt();

			snakeInx[i][0] = tail;
			snakeInx[i][1] = head;
			sc.nextLine();

		}
	}

	private void getLadderIndex(int n) {

		// TODO Auto-generated method stub

		ladderInx = new int[n][2];

		for (int i = 0; i < n; i++) {
			System.out.println("Enter index of Ladder: bottom top");

			int tail = sc.nextInt();
			int head = sc.nextInt();

			ladderInx[i][0] = tail;
			ladderInx[i][1] = head;
			sc.nextLine();

		}

	}

}
