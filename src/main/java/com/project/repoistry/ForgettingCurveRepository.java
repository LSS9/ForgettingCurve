package com.project.repoistry;

import com.project.pojo.ForgettingCurve;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgettingCurveRepository extends CrudRepository<ForgettingCurve, Integer> {



}