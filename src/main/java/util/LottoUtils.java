package util;

import camp.nextstep.edu.missionutils.Console;
import domain.Lotto;
import domain.Money;
import domain.Rank;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static values.Constants.Console.TOTAL_PROFIT_PERCENT;
import static values.Constants.Digit.*;
import static values.Constants.Error.*;
import static values.Constants.Util.LOTTO_NUMBER_SEPARATOR;

public class LottoUtils {

    public static void validate(List<Integer> numbers) {
        validateNumbersSize(numbers);
        validateDuplication(numbers);
        validateNumbersRange(numbers);
    }

    public static void validate(int bonusNumber) {
        if (!(bonusNumber >= 1 && bonusNumber <= 45)) {
            throw new IllegalArgumentException(RANGE_ERROR);
        }
    }


    private static void validateNumbersSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(SIZE_ERROR);
        }
    }

    private static void validateDuplication(List<Integer> numbers) {
        List<Integer> duplicationRemoved = numbers.stream().distinct().collect(Collectors.toList());

        if (duplicationRemoved.size() != 6) {
            throw new IllegalArgumentException(DUPLICATION_ERROR);
        }
    }

    private static void validateNumbersRange(List<Integer> numbers) {
        numbers.forEach(num -> {
            if (!(num >= 1 && num <= 45)) {
                throw new IllegalArgumentException(RANGE_ERROR);
            }
        });
    }

    public static int getLottoCntByMoney(Money money) {
        return money.getMoney() / LOTTO_PRICE;
    }

    private static void printLottoNumbers(List<Lotto> lottos) {
        lottos.forEach(System.out::println);
    }

    public static void printTotalProfitMessage(String percent) {
        System.out.printf(TOTAL_PROFIT_PERCENT, percent);
    }

    public static List<Integer> getWinningNumbers() {
        List<Integer> winningNumbers = new ArrayList<>();
        String inputNumbers = Console.readLine();
        String[] splitNumbers = inputNumbers.split(LOTTO_NUMBER_SEPARATOR);

        for (String splitNumber : splitNumbers) {
            int number = Integer.parseInt(splitNumber);
            winningNumbers.add(number);
        }

        return winningNumbers;
    }

    public static int getBonusNumber() {
        int bonusNumber = Integer.parseInt(Console.readLine());
        return bonusNumber;
    }


    public static String getProfitPercentage(Rank rank, int inputMoney) {
        int totalProfit = calculateProfit(rank.getRank());
        double profitPercent = totalProfit / inputMoney;
        String roundedPercent = String.format("%.1f", profitPercent);

        return roundedPercent;
    }

    private static int calculateProfit(LinkedHashMap<Integer, Integer> rankData) {
        int totalProfit = 0;
        Set<Integer> ranking = rankData.keySet();

        for (Integer rank : ranking) {
            Integer cnt = rankData.get(rank);
            if (rank.equals(FIRST_PLACE)) {
                totalProfit += FIRST_PLACE_PRIZE * cnt;
            } else if (rank.equals(SECOND_PLACE)) {
                totalProfit += SECOND_PLACE_PRIZE * cnt;
            } else if (rank.equals(THIRD_PLACE)){
                totalProfit += THIRD_PLACE_PRIZE * cnt;
            } else if (rank.equals(FOURTH_PLACE)){
                totalProfit += FOURTH_PLACE_PRIZE * cnt;
            } else if (rank.equals(FIFTH_PLACE)) {
                totalProfit += FIFTH_PLACE_PRIZE * cnt;
            }
        }
        return  totalProfit;
    }


}