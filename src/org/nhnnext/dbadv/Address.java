package org.nhnnext.dbadv;

//주소록 데이
public class Address
{
	String name;
	String tel;

	public Address( String name, String tel )
	{
		this.name = name;
		this.tel = tel;
	}
	
	@Override
	public String toString()
	{
		return name + " " + tel;
	}
}
