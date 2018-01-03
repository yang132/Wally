/**
 * Wally
 * 
 * @author Phoebus Yang, github.com/yang132 and Alec Hartline, alechartline.github.io
 * 
 * @version 17 September 2017
 * 
 */



package com.helloworld.dilbert;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Commands {
	public static Stack<Integer> n = new Stack<Integer>();
	public static int acc;
	public static int lineNum;
	public static Scanner s = new Scanner(System.in);
	public static Random r = new Random();
	public static ArrayList<Integer> LoopStarts = new ArrayList<Integer>();
	public static ArrayList<Integer> LoopEnds = new ArrayList<Integer>();

	public static void comm(int c) {
		switch (c) {
		case 1:
			n.push(n.pop() + n.pop()); // +
			break;
		case 2:
			n.push(-n.pop() + n.pop()); // second from top - top
			break;
		case 3:
			n.push(n.pop() * n.pop()); // multiply
			break;
		case 4: {
			int denom = n.pop(); // second from top divided by top
			int num = n.pop();
			n.push(num / denom);
			break;
		}

		case 5:
			n.push(n.pop() ^ n.pop());
			break;
		case 6:
			n.push(s.nextInt()); // console input
			break;
		case 7:
			System.out.print(n.peek()); // output
			break;

		// looping
		case 8: {
			if (n.isEmpty())
				n.push(0);
			if (n.peek() == 0) {
				for (int i = 0; i < LoopStarts.size(); i++) {
					if (LoopStarts.get(i) == lineNum)
						lineNum = LoopEnds.get(i);
				}
				n.pop();
			}
			break;
		}
		case 9: {
			for (int i = 0; i < LoopEnds.size(); i++) {
				if (LoopEnds.get(i) == lineNum) {
					lineNum = LoopStarts.get(i) - 1;
				}
			}

			break;
		}

		case 10:
			n.pop(); // pop top value
			break;
		case 11:
			n.push(1); // push 1
			break;
		case 12:
			System.out.print(Character.toChars(n.peek()));// output character
			break;
		case 13:
			acc = n.pop(); // pop into accumulator
			break;
		case 14:
			n.push(acc); // push accumulator value
			break;
		case 15: {
			int num1 = n.pop(); // swap top two values
			int num2 = n.pop();
			n.push(num1);
			n.push(num2);
			break;
		}
		case 16:
			System.out.println(n); // print stack
			break;
		case 17:
			System.out.println(); // print new line
			break;
		case 18:
			n.push(n.pop() * 10); // multiplies by 10
			break;
		case 19: {
			int num1 = n.pop(); 	// modulus
			int num2 = n.pop();
			n.push(num2 % num1);
			break;
		}
		case 20: {
			int num = n.pop();
			if (n.peek() == num) // equals comparison
			{
				n.push(num);
				n.push(1);
			} else {
				n.push(num);
				n.push(0);
			}
			break;
		}
		case 21: {
			int num = n.pop();
			if (n.peek() > num) // > comparison
			{
				n.push(num);
				n.push(1);
			} else {
				n.push(num);
				n.push(0);
			}
			break;
		}
		case 22: {
			int num = n.pop();
			if (n.peek() < num) // < comparison
			{
				n.push(num);
				n.push(1);
			} else {
				n.push(num);
				n.push(0);
			}
			break;
		}
		case 23: {
			int num = n.pop();
			if (n.peek() >= num) // >= comparison
			{
				n.push(num);
				n.push(1);
			} else {
				n.push(num);
				n.push(0);
			}
			break;
		}
		case 24: {
			int num = n.pop();
			if (n.peek() <= num) // <= comparison
			{
				n.push(num);
				n.push(1);
			} else {
				n.push(num);
				n.push(0);
			}
			break;
		}
		case 25: {
			int num = n.pop();
			if (n.peek() != num) // not equals comparison
			{
				n.push(num);
				n.push(1);
			} else {
				n.push(num);
				n.push(0);
			}
			break;
		}
		
		case 26:{
				int num1 = n.pop();
				int num2 = n.pop();
				n.push(r.nextInt(Math.max(num1, num2)) + Math.min(num1,  num2));
			break;
		}

		default:
			;
		}
	}

	public static void runCommands(int[] list) {
		int index = 0;
		for (int x = 0; x < list.length; x++) {
			if (list[x] == 8) {
				LoopStarts.add(index, x);
				LoopEnds.add(index, null);
				index++;
			} else if (list[x] == 9) {
				for (int j = index - 1; j >= 0; j--) {
					if (LoopEnds.get(j) == null) {
						LoopEnds.set(j, x);
						break;
					}
				}
			}
		}
		// System.out.println(LoopStarts);
		// System.out.println(LoopEnds);

		for (lineNum = 0; lineNum < list.length; lineNum++) {
			comm(list[lineNum]);
		}

	}

}
