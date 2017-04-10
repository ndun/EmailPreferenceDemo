package com.nfd.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nfd.model.EmailPreference;

public class EmailPreferenceDAOImpl implements EmailPreferenceDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addEmailPreference(EmailPreference preference) {
		sessionFactory.getCurrentSession().saveOrUpdate(preference);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmailPreference> getAllEmailPreferences() {
		 return sessionFactory.getCurrentSession().createQuery("from EmailPreference")
                .list();
	}

	@Override
	public void deleteEmailPreference(Integer preferenceId) {
		EmailPreference preference = (EmailPreference) sessionFactory.getCurrentSession().load(
				EmailPreference.class, preferenceId);
        if (preference != null) {
            this.sessionFactory.getCurrentSession().delete(preference);
        }
	}

	@Override
	public EmailPreference updateEmailPreference(EmailPreference preference) {
		sessionFactory.getCurrentSession().saveOrUpdate(preference);
		return preference;
	}

	@Override
	public EmailPreference getEmailPreference(int preferenceId) {
		 return (EmailPreference) sessionFactory.getCurrentSession().get(
				 EmailPreference.class, preferenceId);
	}
	
	@Override
	public EmailPreference findByEmailAddress(String emailAddress) {
		String queryString = "FROM EmailPreference e WHERE e.emailAddress = :emailAddress";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString);
		query.setString("emailAddress", emailAddress);
		List results = query.list();
		if(results == null || results.isEmpty())
			return null;
		return (EmailPreference) results.get(0);
	}

}
