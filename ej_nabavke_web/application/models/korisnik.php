<?php

class korisnik extends CI_Model {

    // function that inserts new user
    function insert_user($email, $name)
    {
      $object = array(
          "email" => $email,
          "ime_prezime" => $name,
      );

      $this->db->insert("korisnik", $object);
      return $this->db->insert_id();
    }

}