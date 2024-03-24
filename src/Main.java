/*
 * Main
 *
 * Version 1.0
 *
 * March 2024
 *
 * Practice work for java
 */

import java.util.Map;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);// создаем сканнер для input
        System.out.println("Введите имя входного файла, который находится в папке Files");
        String inputFileName = in.nextLine();
        System.out.println("Введите имя выходного файла");
        String outputFileName = in.nextLine();
        HashMap<Character, Integer> symbols = new HashMap<>();// создаем hashmap для хранения символов и их количества

        try {//try block
            File inputFile = new File("Files/" + inputFileName);// создаем объект File для input file
            Scanner scanner = new Scanner(inputFile);// создаем объект Scanner для чтения из input file

            while (scanner.hasNextLine()) {//проходим по каждой строчке
                String text = scanner.nextLine();
                for(int i = 0; i < text.length(); i++){
                    char c = text.charAt(i);
                    if(Character.isLetter(c)){//проверяем если char буква
                        symbols.put(c, symbols.getOrDefault(c, 0) + 1);//увеличиваем количество
                    }
                }
            }
            scanner.close();//закрываем scanner
        } catch (FileNotFoundException ex) {//catch block, если file не найден
            System.out.println("Ошибка: Файл не найден.");
            return;
        } catch (IOException ex) {//catch block дла ошибки ввода/вывода
            System.out.println("Ошибка ввода/вывода: " + ex.getMessage());
            return;
        }

        try (FileWriter writer = new FileWriter("Files/" + outputFileName, false)) {//try block для output file
            for(Map.Entry<Character, Integer> entry : symbols.entrySet()) {//записываем в output
                writer.append(entry.getKey() + " - " + entry.getValue());
                writer.append('\n');
            }
            System.out.println("Результаты подсчета символов записаны в файл " + outputFileName);
        } catch (IOException ex) {//catch block для ошибки ввода/вывода
            System.out.println("Ошибка ввода/вывода при записи в файл: " + ex.getMessage());
        }
    }
}
