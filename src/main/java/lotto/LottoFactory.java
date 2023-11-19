package lotto;

import java.util.*;

public class LottoFactory {
    private final static int NUMBER_COUNT = 6;
    private final static int MIN_NUMBER = 1;
    private final static int MAX_NUMBER = 10;
    private static final List<LottoNumber> ALL_LOTTO_NUMBERS = lottoAllNumbers();

    private static List<LottoNumber> lottoAllNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
            lottoNumbers.add(new LottoNumber(number));
        }

        return lottoNumbers;
    }

    public static List<Lotto> createLottoList(int count) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoList.add(new Lotto(createLotto()));
        }

        return Collections.unmodifiableList(lottoList);
    }

    public static List<LottoNumber> createLotto(String inputNumbers) {
        String[] arrayNumbers = inputNumbers.split(",");
        Set<LottoNumber> setLottoNumbers = new HashSet<>();
        for (String value : arrayNumbers) {
            setLottoNumbers.add(new LottoNumber(Integer.parseInt(value.trim())));
        }

        if(setLottoNumbers.size() != NUMBER_COUNT) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않은 6자리 숫자여야 합니다");
        }

        List<LottoNumber> lottoNumbers = new ArrayList<>(setLottoNumbers);
        return Collections.unmodifiableList(lottoNumbers);
    }

    public static List<LottoNumber> createLotto() {
        List<LottoNumber> randomLottoNumbers = sortedRandomNumbers();
        return Collections.unmodifiableList(randomLottoNumbers);
    }

    private static List<LottoNumber> sortedRandomNumbers() {
        Collections.shuffle(LottoFactory.ALL_LOTTO_NUMBERS);
        List<LottoNumber> randomLottoNumbers = new ArrayList<>();
        for (int i = 0; i < NUMBER_COUNT; i++) {
            randomLottoNumbers.add(LottoFactory.ALL_LOTTO_NUMBERS.get(i));
        }

        randomLottoNumbers.sort(Comparator.comparingInt(LottoNumber::lottoNumber));
        return randomLottoNumbers;
    }
}