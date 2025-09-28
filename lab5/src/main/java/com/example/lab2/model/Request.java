package com.example.lab2.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
    /**
     * Уникальный идентификатор сообщения
     */
    @NotBlank(message = "UID не может быть пустым")
    @Size(max = 32)
    private String uid;
    /**
     * Уникальный идентификатор операции
     */
    @NotBlank
    @Size(max = 32)
    private String operationUid;
    /**
     * Имя системы отправителя
     */
    private Systems systemName;
    /**
     * Время создания сообщения
     */
    @NotBlank
    private String systemTime;
    /**
     * Наименование ресурса
     */
    private String source;
    /**
     * Должность сотрудника
     */
    private Positions position;
    /**
     * Базовая зарплата сотрудника
     */
    private double salary;
    /**
     * Коэффициент для расчета премии
     */
    private double bonus;
    /**
     * Количество отработанных дней
     */
    private int workDays;
    /**
     * Уникальный идентификатор коммуникации
     */
    @Min(value = 1)
    @Max(value = 100000)
    private int communicationId;
    /**
     * Уникальный идентификатор шаблона
     */
    private int templateId;
    /**
     * Код продукта
     */
    private int productCode;
    /**
     * СМС код
     */
    private int smsCode;
}