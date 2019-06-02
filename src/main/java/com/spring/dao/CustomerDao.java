package com.spring.dao;

import com.spring.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomerDao {

    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public CustomerDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public CustomerDto saveCustomer(CustomerDto customerDto, String userName) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        StringBuilder sql = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (customerDto.getCustomerNo() == null) {

            sql.append(" INSERT INTO spring_boot.customer ( name, create_date, create_by, update_date, update_by) ");
            sql.append(" VALUES (:customerName, sysdate(), :userName, sysdate(), :userName) ");

            params.put("customerName", customerDto.getCustomerName());
            params.put("userName", userName);

            namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource(params), keyHolder);
            customerDto.setCustomerNo(keyHolder.getKey());
        } else {
            sql.append("  UPDATE spring_boot.customer ");
            sql.append("  SET name        = :customerName, ");
            sql.append("    update_date = sysdate(), ");
            sql.append("    update_by = :userName ");
            sql.append("  WHERE customer_no = :customerNo ");

            params.put("customerName", customerDto.getCustomerName());
            params.put("userName", userName);
            params.put("customerNo", customerDto.getCustomerNo());

            namedParameterJdbcTemplate.update(sql.toString(), new MapSqlParameterSource(params));
        }

        return customerDto;

    }

    public List<CustomerDto> findAllCustomer() {

        StringBuilder sql = new StringBuilder();

        sql.append("select customer_no customer_no,name customer_name from customer");

        List<CustomerDto> result = namedParameterJdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(CustomerDto.class));

        return result;

    }
}
