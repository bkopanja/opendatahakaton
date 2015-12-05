<?php

require(APPPATH . '/libraries/REST_Controller.php');

class api_v1 extends REST_Controller {

    function register_user_post() {

        $this->load->model('korisnik');

        $email = $this->_args["email"];
        $name = $this->_args["name"];

        $userId = $this->korisnik->insert_user($email, $name);

        $envelope = new stdClass;
        $envelope->user_id = $userId;
        $envelope->info = "Copyright © 2015 Ej Nabavke";

        $this->response($envelope, 200);
    }

    function update_user_post() {
        $this->load->model('korisnik');

        if(isset($this->_args["id"])) {
            $userId = $this->_args["id"];
        } else {
            $envelope = new stdClass;
            $envelope->error = "Missing user ID";
            $envelope->info = "Copyright © 2015 Ej Nabavke";

            $this->response($envelope, 500);
        }

        if(isset($this->_args["email"])) {
            $email = $this->_args["email"];
        } else {
            $email = null;
        }

        if(isset($this->_args["name"])) {
            $name = $this->_args["name"];
        } else {
            $name = null;
        }

        if(isset($this->_args["phone"])) {
            $phone = $this->_args["phone"];
        } else {
            $phone = null;
        }

        $updated = $this->korisnik->updateUser($userId, $email, $name, $phone);

        $envelope = new stdClass;
        $envelope->user_updated = $updated;
        $envelope->info = "Copyright © 2015 Ej Nabavke";

        $this->response($envelope, 200);
    }

    function list_cities_post() {
        $this->load->model('mesto');

        $mesta = $this->mesto->getPlaces();

        $envelope = new stdClass;
        $envelope->mesta = $mesta;
        $envelope->info = "Copyright © 2015 Ej Nabavke";

        $this->response($envelope, 200);
    }

    function list_muncipalities_post() {
        $this->load->model('opstina');

        $getMuncipalities = $this->opstina->getMuncipalities();

        $envelope = new stdClass;
        $envelope->opstine = $getMuncipalities;
        $envelope->info = "Copyright © 2015 Ej Nabavke";

        $this->response($envelope, 200);
    }

    function list_purchase_types_post() {
        $this->load->model('vrsta_postupka');

        $purchaseTypes = $this->vrsta_postupka->getPurchaseTypes();

        $envelope = new stdClass;
        $envelope->vrste_nabavke = $purchaseTypes;
        $envelope->info = "Copyright © 2015 Ej Nabavke";

        $this->response($envelope, 200);
    }

    function list_categories_post() {
        $this->load->model('kategorija');

        $categories = $this->kategorija->getCategories();

        $envelope = new stdClass;
        $envelope->kategorije = $categories;
        $envelope->info = "Copyright © 2015 Ej Nabavke";

        $this->response($envelope, 200);
    }

}