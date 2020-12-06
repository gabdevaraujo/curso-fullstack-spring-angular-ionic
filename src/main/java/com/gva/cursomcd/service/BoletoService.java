package com.gva.cursomcd.service;

import java.util.Calendar;
import java.util.Date;

import com.gva.cursomcd.domain.PagamentoComBoleto;

import org.springframework.stereotype.Service;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instante) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(instante);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
	}
    
}