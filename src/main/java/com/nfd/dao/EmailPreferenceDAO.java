package com.nfd.dao;

import java.util.List;

import com.nfd.model.EmailPreference;

public interface EmailPreferenceDAO {
    public void addEmailPreference(EmailPreference preference);
    
    public List<EmailPreference> getAllEmailPreferences();
 
    public void deleteEmailPreference(Integer preferenceId);
 
    public EmailPreference updateEmailPreference(EmailPreference preference);
 
    public EmailPreference getEmailPreference(int preferenceId);
    
    public EmailPreference findByEmailAddress(String emailAddress);
}
