package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nt.model.Grn;
import com.nt.model.GrnDtls;
import com.nt.repository.GrnDtlsRepository;
import com.nt.repository.GrnRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Target.ForArray.ReadOnly;

@Service
public class GrnServiceImpl implements IGrnService{
	@Autowired
	private GrnRepository repo;
	
	@Autowired
	private GrnDtlsRepository dtlsRepo;

	@Override
	@Transactional
	public Integer saveGrn(Grn grn) {
		return repo.save(grn).getId();
	}

	@Override
	@Transactional
	public void updateGrn(Grn grn) {
		repo.save(grn);
		
	}

	@Override
	public void deleteGrn(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Grn> getOneGrn(Integer id) {
		return repo.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Grn> getAllGrn() {
		return repo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isGrnExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public Integer saveGrnDtls(GrnDtls dtls) {
		return dtlsRepo.save(dtls).getId();
	}

	@Override
	public List<GrnDtls> getAllDtlsByGrnId(Integer grnId) {
		return dtlsRepo.getAllDtlsByGrnid(grnId);
	}

	@Override
	@Transactional
	public void updateStatusByGrnDtlsId(String status, Integer id) {
		dtlsRepo.updateStatusBygrnDtlsId(status, id);
		
	}

	


	
	
	

}
