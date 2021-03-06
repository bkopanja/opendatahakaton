<?php

class nabavka extends CI_Model {

    function getMapaPristupacnosti() {
        $str = "SELECT * FROM nabavke WHERE predmet_nabavke IN (
          'Архитектонске Услуге; Инжењерске Услуге; Услуге Урбанистичког Планирања И Пејзажне Архитектуре; Услуге Техничког Тестирања И Анализа; Услуге Енергентског Прегледа И Енергетске Услуге',
          'друге специјализоване делатности повезане са грађевинским радовима (укључујући и тесарске радове)',
          'други грађевински радови и радови специјалних струка',
          'опште грађевински радови',
          'општи радови на зградама и у ниској градњи (без посебних спецификација)',
          'општи радови у области ниске градње'
        )
        ORDER BY datum_poslednje_izmene DESC";
        return $this->db->query($str);
    }

    function getFeed() {
        $str = "SELECT * FROM nabavke ORDER BY datum_poslednje_izmene DESC";
        return $this->db->query($str)->result_object();
    }

}