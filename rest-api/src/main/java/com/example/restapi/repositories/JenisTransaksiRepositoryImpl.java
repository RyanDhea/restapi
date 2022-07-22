package com.example.restapi.repositories;

import com.example.restapi.exceptions.EtResourceNotFoundException;
import com.example.restapi.models.JenisTransaksi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository
public class JenisTransaksiRepositoryImpl  implements JenisTransaksiRepository {

    private static final String SQL_FIND_BY_ID = "SELECT ID_JENIS_TRANSAKSI, TRANSAKSI_DESC FROM ET_JENIS_TRANSAKSI WHERE ID_JENIS_TRANSAKSI = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public JenisTransaksi findById(Integer userId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, jenisTransaksiRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Jenis Transaksi not found");
        }
    }

    private RowMapper<JenisTransaksi> jenisTransaksiRowMapper = ((rs, rowNum) -> {
        return new JenisTransaksi(rs.getInt("JENIS_TRANSAKSI_ID"),
                rs.getString("TRANSAKSI_DESC"));
    });
}
