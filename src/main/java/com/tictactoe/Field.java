package com.tictactoe;

import java.util.*;
import java.util.stream.Collectors;

public class Field {
    private final Map<Integer, Sign> field;

    public Field() {
        field = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            field.put(i, Sign.EMPTY);
        }
    }

    public Map<Integer, Sign> getField() {
        return field;
    }

    public boolean isEmpty(int index) {
        return field.get(index) == Sign.EMPTY;
    }

    public void setCell(int index, Sign sign) {
        field.put(index, sign);
    }

    public boolean hasEmptyCell() {
        return field.values().stream().anyMatch(s -> s == Sign.EMPTY);
    }

    public int getEmptyRandomCell() {
        List<Integer> empty = field.entrySet().stream()
                .filter(e -> e.getValue() == Sign.EMPTY)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (empty.isEmpty()) return -1;
        return empty.get(new Random().nextInt(empty.size()));
    }

    public List<Sign> getFieldData() {
        return field.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public Sign checkWin() {
        List<List<Integer>> winPossibilities = List.of(
                List.of(0, 1, 2),
                List.of(3, 4, 5),
                List.of(6, 7, 8),
                List.of(0, 3, 6),
                List.of(1, 4, 7),
                List.of(2, 5, 8),
                List.of(0, 4, 8),
                List.of(2, 4, 6)
        );

        for (List<Integer> win : winPossibilities) {
            if (field.get(win.get(0)) == field.get(win.get(1))
                    && field.get(win.get(0)) == field.get(win.get(2))
                    && field.get(win.get(0)) != Sign.EMPTY) {
                return field.get(win.get(0));
            }
        }
        return Sign.EMPTY;
    }
}
