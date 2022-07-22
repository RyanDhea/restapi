package com.example.restapi.repositories;

import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.JenisTransaksi;

public interface JenisTransaksiRepository {

	JenisTransaksi findById(Integer jenistransaksiId) throws EtResourceNotFoundException;
}
