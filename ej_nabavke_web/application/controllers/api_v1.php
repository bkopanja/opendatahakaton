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
        $envelope->info = "Copyright © 2015 EJ Nabavke";

        /** @noinspection PhpParamsInspection */
        $this->response($envelope, 200);
    }

    function places_list_get() {
        $this->load->model('place');
        $data = $this->place->get_places_list(true);
        $this->response($data, 200);
    }

    function all_data_get() {
        $this->load->model('place');
        $this->load->model('parking_zone');
        $this->load->model('area');
        $this->load->model('change_set');
        $include_geo = ($this->get('include_geo') == 1);

        $data = $this->place->get_places_list(true);

        // Iterate over places
        foreach ($data as $row_place) {
            // Get parking zones for place
            $parking_zones = $this->parking_zone->get_zones($row_place->place_id);
            $row_place->parking_zones = $parking_zones;

            // Get area polygones for place
            if ($include_geo) {
                $areas = $this->area->get_areas($row_place->place_id);
                foreach ($areas as $row_area) {
                    $row_area->points = $this->area->get_area_points($row_area->area_polygone_id);
                }
                $row_place->areas = $areas;
            }
        }

        $envelope = new stdClass;
        $envelope->change_set_latest = $this->change_set->get_place_change_set_latest();
        $envelope->info = "Copyright © 2013 Parking Manijak Team (parkingmanijak.com)";
        $envelope->places = $data;

        /** @noinspection PhpParamsInspection */
        $this->response($envelope, 200);
    }

    function place_data_get($id = -1) {
    
        if ($id == -1) {
            $this->all_data_get();
            exit(1);
        }

        $this->load->model('place');
        $this->load->model('parking_zone');
        $this->load->model('area');
        $include_geo = ($this->get('include_geo') == 1);

        $data = $this->place->get_place($id);

        // Get parking zones for place
        $parking_zones = $this->parking_zone->get_zones($data->place_id);
        $data->parking_zones = $parking_zones;

        // Get area polygones for place
        if ($include_geo) {
            $areas = $this->area->get_areas($data->place_id);
            foreach ($areas as $row_area) {
                $row_area->points = $this->area->get_area_points($row_area->area_polygone_id);
            }
            $data->areas = $areas;
        }

        $this->response($data, 200);
    }

    function change_set_latest_get() {
        $this->load->model('change_set');
        $data['change_set_latest'] = $this->change_set->get_place_change_set_latest();
        $this->response($data, 200);
    }

    function change_set_places_get() {
        $this->load->model('change_set');
        $data = $this->change_set->get_place_change_sets($this->get('from_change_set'), $this->get('to_change_set'));
        $this->response($data, 200);
    }

    function place_from_geo_get() {
        $this->load->model('place');
        $data = $this->place->get_place_from_geo($this->get('lat'), $this->get('lon'));
        if(!is_object($data))
            $this->response(array("error"=>"No data"), 404);
        else
            /** @noinspection PhpParamsInspection */
            $this->response($data, 200);
    }

    function area_from_geo_get() {
        $this->load->model('place');
        $this->load->model('area');
        $data = $this->place->get_place_from_geo($this->get('lat'), $this->get('lon'));
        if(!is_object($data)) {
            $this->response(array("error"=>"No data"), 404);
            exit(1);
        }

        $data_area = $this->area->get_area_by_geolocation(
            $this->get('lat'),
            $this->get('lon'),
            $data->place_id);

        if(!is_object($data_area))
            $this->response(array("error"=>"No data"), 404);
        else
            /** @noinspection PhpParamsInspection */
            $this->response($data_area, 200);
    }

}