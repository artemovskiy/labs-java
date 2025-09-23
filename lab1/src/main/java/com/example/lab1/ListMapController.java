package com.example.lab1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class ListMapController {

    private ArrayList<String> arrayList;
    private HashMap<Integer, String> hashMap;

    @GetMapping("/update-array")
    public String updateArrayList(@RequestParam(value = "s", required = false) String s) {
        try {
            if (s == null || s.trim().isEmpty()) {
                return "Ошибка: параметр 's' не может быть пустым";
            }

            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
            arrayList.add(s);
            return String.format("Добавлено значение '%s' в ArrayList. Текущий размер: %d", s, arrayList.size());
        } catch (Exception e) {
            return "Ошибка при добавлении элемента в ArrayList: " + e.getMessage();
        }
    }

    @GetMapping("/show-array")
    public String showArrayList() {
        try {
            if (arrayList == null || arrayList.isEmpty()) {
                return "ArrayList пуст или не инициализирован";
            }
            return "Элементы ArrayList: " + arrayList.toString();
        } catch (Exception e) {
            return "Ошибка при получении элементов ArrayList: " + e.getMessage();
        }
    }

    @GetMapping("/update-map")
    public String updateHashMap(@RequestParam(value = "s", required = false) String s) {
        try {
            if (s == null || s.trim().isEmpty()) {
                return "Ошибка: параметр 's' не может быть пустым";
            }

            if (hashMap == null) {
                hashMap = new HashMap<>();
            }
            Integer key = hashMap.size();
            hashMap.put(key, s);
            return String.format("Добавлено значение '%s' с ключом %d в HashMap. Текущий размер: %d", s, key, hashMap.size());
        } catch (Exception e) {
            return "Ошибка при добавлении элемента в HashMap: " + e.getMessage();
        }
    }

    @GetMapping("/show-map")
    public String showHashMap() {
        try {
            if (hashMap == null || hashMap.isEmpty()) {
                return "HashMap пуст или не инициализирован";
            }

            StringBuilder result = new StringBuilder("Элементы HashMap: ");
            for (HashMap.Entry<Integer, String> entry : hashMap.entrySet()) {
                result.append(entry.getKey()).append(" - ").append(entry.getValue()).append(", ");
            }

            if (result.length() > 18) {
                result.setLength(result.length() - 2);
            }

            return result.toString();
        } catch (Exception e) {
            return "Ошибка при получении элементов HashMap: " + e.getMessage();
        }
    }

    @GetMapping("/show-all-length")
    public String showAllLength() {
        try {
            int arrayListSize = (arrayList != null) ? arrayList.size() : 0;
            int hashMapSize = (hashMap != null) ? hashMap.size() : 0;
            return String.format("Количество элементов в ArrayList: %d, в HashMap: %d", arrayListSize, hashMapSize);
        } catch (Exception e) {
            return "Ошибка при подсчете элементов: " + e.getMessage();
        }
    }
}