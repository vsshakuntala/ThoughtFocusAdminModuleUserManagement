package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable/* ,IAuditLog */{
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Boolean active = true;
	private Boolean agreedToTermsOfUse;
	private String alias;
	private Long createdBy;
	private Date createdDate;
	private String email;
	private String phoneNumber;
	private String companyName;

	private Integer failedLoginAttempt;
	private String firstName;
	private Date lastLoginDate;
	private String lastName;
	private Boolean locked;
	private Date lockedDate;
	private String middleName;
	private Long modifiedBy;
	private Date modifiedDate;
	private String password;
	private Date passwordExpiryDate;
	private Boolean resetPassword = false;
	private String userName;
	/*Two fields added*/
	private String customerAccount;
	

	private String address;
	
	private UserPreference userPreference;
	private Set<UserTrail> userTrails;

	private Set<Cart> carts;
	private Set<Cart> orderedCarts;
	private Set<Cart> savedCarts;
	private Cart openCart;

	private Set<Role> roles;
	private Set<Catalog> catalogs;

	private String authenticationToken;
	private String rememberMeToken;
	private Set<UserCatalog> userCatalogs;
	private Set<UserRole> userRoles;
	private Set<UserCustomer> userCustomers;
	private Set<UserAddress> userAddresses;

	private Set<Address> addresses;

	private Address primaryBillToAddress;
	private Address primaryShipToAddress;

	private Set<UserGroup> userGroups;
	private Organization organization;

	private Set<UserOrganizationMap> userOrganizationMap;

	// private Set<Group> groups;

	private String externalAuthenticationToken;
	private String countryFlag;
	
	private Map<String, String> organizationConfigMap;
	public User(Long userId, String email, String firstName, String lastName) {
		super();
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}


	public User() {
		orderedCarts = new HashSet<Cart>();
		savedCarts = new HashSet<Cart>();
		organizationConfigMap = new HashMap<String, String>();
	}

	public User(Long userId, Boolean active, Boolean agreedToTermsOfUse,
			String alias, Long createdBy, Date createdDate, String email,
			Integer failedLoginAttempt, String firstName, Date lastLoginDate,
			String lastName, Boolean locked, Date lockedDate,
			String middleName, Long modifiedBy, Date modifiedDate,
			String password, Date passwordExpiryDate, Boolean resetPassword,
			String userName, String customerAccount, String address, UserPreference userPreference,
			Set<UserTrail> userTrails, Set<Cart> carts, Set<Cart> orderedCarts,
			Set<Cart> savedCarts, Cart openCart, Set<Role> roles,
			Set<Catalog> catalogs, String authenticationToken,
			String rememberMeToken, Set<UserCatalog> userCatalogs,
			Set<UserRole> userRoles, Set<UserCustomer> userCustomers,
			Set<UserAddress> userAddresses, Set<Address> addresses,
			Address primaryBillToAddress, Address primaryShipToAddress,
			Set<UserGroup> userGroups, Organization organization,
			Set<UserOrganizationMap> userOrganizationMap,
			String externalAuthenticationToken,
			Map<String, String> organizationConfigMap) {
		super();
		this.userId = userId;
		this.active = active;
		this.agreedToTermsOfUse = agreedToTermsOfUse;
		this.alias = alias;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.email = email;
		this.failedLoginAttempt = failedLoginAttempt;
		this.firstName = firstName;
		this.lastLoginDate = lastLoginDate;
		this.lastName = lastName;
		this.locked = locked;
		this.lockedDate = lockedDate;
		this.middleName = middleName;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.password = password;
		this.passwordExpiryDate = passwordExpiryDate;
		this.resetPassword = resetPassword;
		this.userName = userName;
		this.customerAccount = customerAccount;
		this.address= address;
		this.userPreference = userPreference;
		this.userTrails = userTrails;
		this.carts = carts;
		this.orderedCarts = orderedCarts;
		this.savedCarts = savedCarts;
		this.openCart = openCart;
		this.roles = roles;
		this.catalogs = catalogs;
		this.authenticationToken = authenticationToken;
		this.rememberMeToken = rememberMeToken;
		this.userCatalogs = userCatalogs;
		this.userRoles = userRoles;
		this.userCustomers = userCustomers;
		this.userAddresses = userAddresses;
		this.addresses = addresses;
		this.primaryBillToAddress = primaryBillToAddress;
		this.primaryShipToAddress = primaryShipToAddress;
		this.userGroups = userGroups;
		this.organization = organization;
		// this.userOrganizationMap = userOrganizationMap;
		this.externalAuthenticationToken = externalAuthenticationToken;
		this.organizationConfigMap = organizationConfigMap;
	}

	@Id
	@Column(name = "USER_ID")
	/*
	 * @SequenceGenerator(name = "UsersSequence", sequenceName =
	 * "NEW_USERS_SEQ")
	 * 
	 * @GeneratedValue(generator="UsersSequence")
	 */
	@GeneratedValue
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cartOwner")
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

	@Column(name = "ACTIVE")
	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "AGREED_TO_TERMS_OF_USE")
	public Boolean getAgreedToTermsOfUse() {
		return this.agreedToTermsOfUse;
	}

	public void setAgreedToTermsOfUse(Boolean agreedToTermsOfUse) {
		this.agreedToTermsOfUse = agreedToTermsOfUse;
	}

	@Column(name = "ALIAS")
	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "FAILED_LOGIN_ATTEMPT")
	public Integer getFailedLoginAttempt() {
		return this.failedLoginAttempt;
	}

	public void setFailedLoginAttempt(Integer failedLoginAttempt) {
		this.failedLoginAttempt = failedLoginAttempt;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_DATE")
	public Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "LOCKED")
	public Boolean getLocked() {
		return this.locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOCKED_DATE")
	public Date getLockedDate() {
		return this.lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	@Column(name = "MIDDLE_NAME")
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PASSWORD_EXPIRY_DATE")
	public Date getPasswordExpiryDate() {
		return this.passwordExpiryDate;
	}

	public void setPasswordExpiryDate(Date passwordExpiryDate) {
		this.passwordExpiryDate = passwordExpiryDate;
	}

	@Column(name = "RESET_PASSWORD")
	public boolean getResetPassword() {
		return this.resetPassword;
	}

	public void setResetPassword(boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "CUSTOMER_ACCOUNT")
	public String getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(String customerAccount) {
		this.customerAccount = customerAccount;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	public UserPreference getUserPreference() {
		return this.userPreference;
	}

	public void setUserPreference(UserPreference userPreference) {
		this.userPreference = userPreference;
	}

	// bi-directional many-to-one association to UserTrail
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserTrail> getUserTrails() {
		return this.userTrails;
	}

	public void setUserTrails(Set<UserTrail> userTrails) {
		this.userTrails = userTrails;
	}

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "USER_CATALOG", joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "CATALOG_ID", referencedColumnName = "CATALOG_ID") })
	public Set<Catalog> getCatalogs() {
		return catalogs;
	}

	@Transient
	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public void setCatalogs(Set<Catalog> catalogs) {
		this.catalogs = catalogs;
	}

	@Transient
	public Set<Cart> getOrderedCarts() {
		return orderedCarts;
	}

	public void setOrderedCarts(Set<Cart> orderedCarts) {
		this.orderedCarts = orderedCarts;
	}

	@Transient
	public Set<Cart> getSavedCarts() {
		return savedCarts;
	}

	public void setSavedCarts(Set<Cart> savedCarts) {
		this.savedCarts = savedCarts;
	}

	@Transient
	public Cart getOpenCart() {
		return openCart;
	}

	public void setOpenCart(Cart openCart) {
		this.openCart = openCart;
	}

	public void setAuthenticationToken(String authenticationToken) {
		this.authenticationToken = authenticationToken;
	}

	@Column(name = "AUTH_TOKEN")
	public String getAuthenticationToken() {
		return authenticationToken;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<UserCatalog> getUserCatalogs() {
		return userCatalogs;
	}

	public void setUserCatalogs(Set<UserCatalog> userCustomer) {
		this.userCatalogs = userCustomer;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<UserCustomer> getUserCustomers() {
		return userCustomers;
	}

	public void setUserCustomers(Set<UserCustomer> userCustomers) {
		this.userCustomers = userCustomers;
	}

	public void setRememberMeToken(String rememberMeToken) {
		this.rememberMeToken = rememberMeToken;
	}

	@Column(name = "REMEMBER_ME_TOKEN")
	public String getRememberMeToken() {
		return rememberMeToken;
	}

	public void setUserAddresses(Set<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	@Transient
	public Address getPrimaryBillTo() {
		return primaryBillToAddress;
	}

	public void setPrimaryBillTo(Address primaryBillToAddress) {
		this.primaryBillToAddress = primaryBillToAddress;
	}

	@Transient
	public Address getPrimaryShipTo() {
		return primaryShipToAddress;
	}

	public void setPrimaryShipTo(Address primaryShipToAddress) {
		this.primaryShipToAddress = primaryShipToAddress;
	}

	public void setExternalAuthenticationToken(
			String externalAuthenticationToken) {
		this.externalAuthenticationToken = externalAuthenticationToken;
	}

	@OneToOne
	@JoinColumn(name = "ORGANIZATION_ID")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public Set<UserOrganizationMap> getUserOrganizationMap() {
		return userOrganizationMap;
	}

	public void setUserOrganizationMap(
			Set<UserOrganizationMap> userOrganizationMap) {
		this.userOrganizationMap = userOrganizationMap;
	}

	@Column(name = "EXTERNAL_AUTH_TOKEN")
	public String getExternalAuthenticationToken() {
		return externalAuthenticationToken;
	}

	@Column(name = "COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return User associated active Catalog(s).
	 */
	public Set<Catalog> fetchActiveCatalogs() {
		Set<Catalog> activeCats = new HashSet<Catalog>();

		for (UserCatalog userCatalog : getUserCatalogs()) {
			if (userCatalog.getActive())
				for (Catalog catalog : getCatalogs()) {
					if ((userCatalog.getId().getCatalogId() == catalog
							.getCatalogId()) && catalog.getActive()) {
						activeCats.add(catalog);
						break;
					}
				}
		}

		return activeCats;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

	/*
	 * @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) public Set<Group>
	 * getGroups() { return groups; } public void setGroups(Set<Group> groups) {
	 * this.groups = groups; }
	 */

	/**
	 * @return User associated active Role(s).
	 */
	public Set<Role> fetchActiveRoles() {
		Set<Role> activeRoles = new HashSet<Role>();

		for (UserRole userRole : getUserRoles()) {
			if (userRole.getActive())
				for (Role role : getRoles()) {
					if ((userRole.getId().getRoleId() == role.getRoleId())
							&& role.getActive()) {
						activeRoles.add(role);
						break;
					}
				}
		}

		return activeRoles;
	}

	/**
	 * @return User associated active Customer(s).
	 */
	public Set<Customer> fetchActiveCustomers() {
		Set<Customer> activeCustomers = new HashSet<Customer>();

		for (UserCustomer userCustomer : getUserCustomers()) {
			if (userCustomer.getActive()) {
				Customer customer = userCustomer.getCustomer();
				if (customer.getActive()) {
					activeCustomers.add(customer);
				}
			}
		}
		return activeCustomers;
	}

	/**
	 * @return User active UserCustomer(s).
	 */
	public Set<UserCustomer> fetchActiveUserCustomers() {
		Set<UserCustomer> activeCustomers = new HashSet<UserCustomer>();

		for (UserCustomer userCustomer : getUserCustomers()) {
			if (userCustomer.getActive()) {
				activeCustomers.add(userCustomer);
			}
		}
		return activeCustomers;
	}

	public void mergeUser(User user) {

		this.active = user.getActive();
		this.agreedToTermsOfUse = user.getAgreedToTermsOfUse();
		this.alias = user.getAlias();
		this.createdBy = user.getCreatedBy();
		this.createdDate = user.getCreatedDate();
		this.email = user.getEmail();
		this.failedLoginAttempt = user.getFailedLoginAttempt();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.lastLoginDate = user.getLastLoginDate();
		this.locked = user.getLocked();
		this.lockedDate = user.getLockedDate();
		this.middleName = user.getMiddleName();
		this.modifiedBy = user.getModifiedBy();
		this.modifiedDate = user.getModifiedDate();
		this.password = user.getPassword();
		this.passwordExpiryDate = user.getPasswordExpiryDate();
		this.resetPassword = user.getResetPassword();
	}

	public void mergePersnalInfo(User user, Long modifiedBy) {

		this.firstName = user.getFirstName();
		this.middleName = user.getMiddleName();
		this.lastName = user.getLastName();
		this.modifiedBy = modifiedBy;
		this.modifiedDate = new java.sql.Timestamp(
				new java.util.Date().getTime());

		// TODO: Fix this for Milacron changes
		/*
		 * for (Address address : addresses) { address.mergeAddress(user,
		 * modifiedBy); // iterating only once coz User always gonna have one
		 * instance of // address(1 TO 1 relationship) break; }
		 */
	}

	public void mergeAccountInfo(User user, Long modifiedBy) {

		this.email = user.getEmail();
		this.userName = user.getUserName();
		if (user.getPassword() != null) {
			this.password = user.getPassword();
		}
		this.modifiedBy = modifiedBy;
		this.modifiedDate = new java.sql.Timestamp(
				new java.util.Date().getTime());

	}

	@Transient
	public Map<String, String> getOrganizationConfigMap() {
		for (OrganizationConfig oc : this.organization.getOrganizationConfigs()) {
			organizationConfigMap.put(oc.getId().getConfigName(),
					oc.getConfigValue());
		}
		return organizationConfigMap;
	}

	public void setOrganizationConfigMap(
			Map<String, String> organizationConfigMap) {
		this.organizationConfigMap = organizationConfigMap;
	}

	public Map<String, String> organizationConfigMap() {
		Map<String, String> organizationConfigMap = new HashMap<String, String>();
		for (OrganizationConfig oc : this.organization.getOrganizationConfigs()) {
			organizationConfigMap.put(oc.getId().getConfigName(),
					oc.getConfigValue());
		}
		return organizationConfigMap;
	}

	public String validate() {

		StringBuilder errors = new StringBuilder();

		if (firstName == null || firstName == "") {
			errors.append("First name can not be empty.");
		}
		if (lastName == null || lastName == "") {
			errors.append("\n Last name can not be empty.");
		}
		if (email == null || email == "") {
			errors.append("\n Email can not be empty.");
		}
		if (locked != null) {
			setLockedDate(new java.sql.Timestamp(new java.util.Date().getTime()));
		}
		if (password == null || password == "") {
			errors.append("\n Password can not be empty.");
		}
		if (userName == null || userName == "") {
			errors.append("\n User Name can not be empty.");
		}

		return errors.toString();
	}

	public Set<Address> fetchActiveAddresses() {
		Set<Address> activeAddresses = new HashSet<Address>();

		for (UserAddress userAddress : getUserAddresses()) {
			if (userAddress.getActive()) {
				activeAddresses.add(userAddress.getAddress());
			}
		}

		this.addresses = activeAddresses;

		return activeAddresses;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", active=" + active
				+ ", agreedToTermsOfUse=" + agreedToTermsOfUse + ", alias="
				+ alias + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", email=" + email + ", failedLoginAttempt="
				+ failedLoginAttempt + ", firstName=" + firstName
				+ ", lastLoginDate=" + lastLoginDate + ", lastName=" + lastName
				+ ", locked=" + locked + ", lockedDate=" + lockedDate
				+ ", middleName=" + middleName + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", password=" + password
				+ ", passwordExpiryDate=" + passwordExpiryDate
				+ ", resetPassword=" + resetPassword + ", userName=" + userName
				+ ", userPreference=" + userPreference + ", userTrails="
				+ userTrails + ", carts=" + carts + ", orderedCarts="
				+ orderedCarts + ", savedCarts=" + savedCarts + ", openCart="
				+ openCart + ", roles=" + roles + ", catalogs=" + catalogs
				+ ", authenticationToken=" + authenticationToken
				+ ", rememberMeToken=" + rememberMeToken + ", userCatalogs="
				+ userCatalogs + ", userRoles=" + userRoles
				+ ", userCustomers=" + userCustomers + ", userAddresses="
				+ userAddresses + ", addresses=" + addresses
				+ ", primaryBillToAddress=" + primaryBillToAddress
				+ ", primaryShipToAddress=" + primaryShipToAddress
				+ ", userGroups=" + userGroups + ", organization="
				+ organization + ",  externalAuthenticationToken="
				+ externalAuthenticationToken + ", organizationConfigMap="
				+ organizationConfigMap + "]";
	}

	/**
	 * return user organization approved list
	 */
	public List<UserOrganizationMap> fetchUserOrganizationMaps() {
		List<UserOrganizationMap> userOrgMap = new ArrayList<UserOrganizationMap>();
		for (UserOrganizationMap userOrg : getUserOrganizationMap()) {
			if (userOrg.getApprovalStatus() && userOrg.getTermsCondition()) {
				userOrgMap.add(userOrg);
			}
		}
		return userOrgMap;
	}

	/*
	 * @Transient
	 * 
	 * @Override public Long getId(){ return this.userId; }
	 * 
	 * @Transient
	 * 
	 * @Override public String getLogDetail(){ StringBuilder sb = new
	 * StringBuilder(); sb.append(" User Id : ").append(userId)
	 * .append(" User Name : ").append(userName)
	 * .append(" User Firstname : ").append(firstName)
	 * .append(" User Lastname : ").append(lastName);
	 * 
	 * logger.debug("*************************************************"+sb)
	 * ;
	 * 
	 * return sb.toString(); }
	 */
	@Column(name = "COUNTRY_FLAG")
	public String getCountryFlag() {
		return countryFlag;
	}


	public void setCountryFlag(String countryFlag) {
		this.countryFlag = countryFlag;
	}
}