package com.roze.admin.setting;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.roze.common.entity.setting.Setting;
import com.roze.common.entity.setting.SettingCategory;

public interface SettingRepository extends CrudRepository<Setting, String> {
	public List<Setting> findByCategory(SettingCategory category);
}
