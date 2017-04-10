package com.nfd.service;

import java.util.List;

import com.nfd.model.EmailPreference;


public interface EmailPreferenceService {
	
    public void addEmailPreference(EmailPreference preference);
    
    public List<EmailPreference> getAllEmailPreferences();
 
    public void deleteEmailPreference(Integer preferenceId);
 
    public EmailPreference getEmailPreference(int preferenceId);
 
    public EmailPreference updateEmailPreference(EmailPreference preference);
    
	public EmailPreference findByEmailAddress(EmailPreference preference);

	public boolean doesEmailPreferenceExist(EmailPreference preference);
}
