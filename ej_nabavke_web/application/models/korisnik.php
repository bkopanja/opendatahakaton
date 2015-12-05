<?php

class korisnik extends CI_Model {

    // function that inserts new user
    function insert_user($email, $name)
    {

        $user = $this->getUser($email);

        if(count($user) == 0) {

            $object = array(
                "email" => $email,
                "ime_prezime" => $name,
            );

            $this->db->insert("korisnik", $object);
            return $this->db->insert_id();
        } else {
            return $user[0]->id;
        }
    }

    function updateUser($id, $email, $name, $phone) {
        $sql = "UPDATE korisnik SET";

        if(!is_null($email)) {
            $sql .= " email = '$email',";
        }

        if(!is_null($phone)) {
            $sql .= " telefon = '$phone',";
        }

        if(!is_null($name)) {
            $sql .= " ime_prezime = '$name'";
        }

        $sql .= " WHERE id = {$id}";

        return $this->db->query($sql);

    }

    function getUser($email) {
        $query_sql = 'SELECT * FROM korisnik WHERE email = ?;';
        $query = $this->db->query($query_sql, array($email));
        return $query->result_object();
    }

}