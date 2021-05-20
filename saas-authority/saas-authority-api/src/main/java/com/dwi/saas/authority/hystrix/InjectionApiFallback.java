package com.dwi.saas.authority.hystrix;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.dwi.saas.authority.InjectionApi;

public class InjectionApiFallback implements InjectionApi{

	@Override
	public Map<Serializable, Object> findUserByIds(Set<Serializable> ids) {
		return Collections.emptyMap();
	}

	@Override
	public Map<Serializable, Object> findUserNameByIds(Set<Serializable> ids) {
		return Collections.emptyMap();
	}

	@Override
	public Map<Serializable, Object> findStationByIds(Set<Serializable> ids) {
		return Collections.emptyMap();
	}

	@Override
	public Map<Serializable, Object> findStationNameByIds(Set<Serializable> ids) {
		return Collections.emptyMap();
	}

	@Override
	public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
		return Collections.emptyMap();
	}

	@Override
	public Map<Serializable, Object> findOrgByIds(Set<Serializable> ids) {
		return Collections.emptyMap();
	}

	@Override
	public Map<Serializable, Object> findOrgNameByIds(Set<Serializable> ids) {
		return Collections.emptyMap();
	}

}
