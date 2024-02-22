/* COMPSCI 424 Program 1
 * Name: Will Anthoney
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> commands = new LinkedList<>();

        System.out.println("Enter commands (create N, destroy N, or end):");
        String input;
        while (!(input = sc.nextLine()).equals("end")) {
            commands.add(input);
        }

        Version1 version1 = new Version1(16);
        Version2 version2 = new Version2(16);

        System.out.println("Version 1:");
        for (String i : commands) {
            String[] parts = i.split(" ");
            int pid = Integer.parseInt(parts[1]);
            if (parts[0].equals("create")) {
                version1.create(pid / 10, pid % 10);
            } else if (parts[0].equals("destroy")) {
                version1.destroy(pid);
            }
            version1.showProcessInfo();
        }

        System.out.println("Version 2:");
        for (String i : commands) {
            String[] parts = i.split(" ");
            int pid = Integer.parseInt(parts[1]);
            if (parts[0].equals("create")) {
                version2.create(pid / 10, pid % 10);
            } else if (parts[0].equals("destroy")) {
                version2.destroy(pid);
            }
            version2.showProcessInfo();
        }

        long startTimeV1 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            for (String action : commands) {
                String[] parts = action.split(" ");
                int pid = Integer.parseInt(parts[1]);
                if (parts[0].equals("create")) {
                    version1.create(pid / 10, pid % 10);
                } else if (parts[0].equals("destroy")) {
                    version1.destroy(pid);
                }
            }
        }
        long endTimeV1 = System.currentTimeMillis();
        long elapsedTimeV1 = endTimeV1 - startTimeV1;
        System.out.println("Version 1 Running Time: " + elapsedTimeV1 + " milliseconds");

        long startTimeV2 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            for (String action : commands) {
                String[] parts = action.split(" ");
                int pid = Integer.parseInt(parts[1]);
                if (parts[0].equals("create")) {
                    version2.create(pid / 10, pid % 10);
                } else if (parts[0].equals("destroy")) {
                    version2.destroy(pid);
                }
            }
        }
        long endTimeV2 = System.currentTimeMillis();
        long elapsedTimeV2 = endTimeV2 - startTimeV2;
        System.out.println("Version 2 Running Time: " + elapsedTimeV2 + " milliseconds");
    }
}
