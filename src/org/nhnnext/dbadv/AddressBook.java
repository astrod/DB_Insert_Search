package org.nhnnext.dbadv;

public interface AddressBook
{
	public boolean insert( int ssn, String name, String tel );
	public boolean delete( int ssn );
	public boolean update( int ssn, String name, String tel );
	public Address search( int ssn );
}
