package ru.rshb.intech.digital.feature.startups.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Описывает входные данные о компании в строке входного файла.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class StartupRecord {

  /**
   * Название
   */
  private String title;

  /**
   * Краткое описание
   */
  private String summary;

  /**
   * Полное описание
   */
  private String description;

  /**
   * Ссылка полная
   */
  private String siteLink;

  /**
   * Кол-во сотрудников
   */
  private String workers;

  /**
   * Год основания
   */
  private String year;

  /**
   * Логотип
   */
  private String logo;

  /**
   * Раунд
   */
  private String round;

  /**
   * Кол-во клиентов
   */
  private String integrations;

  /**
   * Стадия
   */
  private String etap;

  /**
   * Категория
   */
  private String category;

  /**
   * Страна
   */
  private String country;

  /**
   * Город
   */
  private String city;

  /**
   * В архиве
   */
  private String archieve;

  /**
   * Округ
   */
  private String district;

  /**
   * Регион
   */
  private String region;

  /**
   * Инвестиции
   */
  private String money;

  /**
   * Валюта инвестиций
   */
  private String moneyType;

  /**
   * Доп. скрытая информация
   */
  private String hidden;

  /**
   * Доп. пдф
   */
  private String file;
}
