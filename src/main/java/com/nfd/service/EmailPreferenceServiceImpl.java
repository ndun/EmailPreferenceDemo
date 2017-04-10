package com.nfd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nfd.dao.EmailPreferenceDAO;
import com.nfd.model.EmailPreference;

@Service("emailPreferenceService")
@Transactional
public class EmailPreferenceServiceImpl implements EmailPreferenceService {

	@Autowired
	private EmailPreferenceDAO emailDAO;
	
	@Override
	public void addEmailPreference(EmailPreference preference) {
		emailDAO.addEmailPreference(preference);
	}

	@Override
	public List<EmailPreference> getAllEmailPreferences() {
		return emailDAO.getAllEmailPreferences();
	}

	@Override
	public void deleteEmailPreference(Integer preferenceId) {
		emailDAO.deleteEmailPreference(preferenceId);
	}

	@Override
	public EmailPreference getEmailPreference(int preferenceId) {
		return emailDAO.getEmailPreference(preferenceId);
	}

	@Override
	public EmailPreference updateEmailPreference(EmailPreference preference) {
		return emailDAO.updateEmailPreference(preference);
	}
	
	@Override
	public EmailPreference findByEmailAddress(EmailPreference preference) {
		if(preference.getEmailAddress() == null)
			return null;
		
		return emailDAO.findByEmailAddress(preference.getEmailAddress());
	}
	
	@Override
	public boolean doesEmailPreferenceExist(EmailPreference preference) {
		return findByEmailAddress(preference) != null;
	}

}
