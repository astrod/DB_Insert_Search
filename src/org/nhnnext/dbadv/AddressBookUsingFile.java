package org.nhnnext.dbadv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class AddressBookUsingFile implements AddressBook
{	
	private HashMap<Integer, Address> hashMap = new HashMap<Integer, Address>();

	//Hashmap에 데이터를 삽입하는 코드 
	@Override
	public boolean insert( int ssn, String name, String tel )
	{
		if ( hashMap.containsKey( ssn ) )
			return false;

		hashMap.put( ssn, new Address( name, tel ) );
		return true;
	}
	
	//Hashmap에 데이터를 삭제하는 코드 
	@Override
	public boolean delete( int ssn )
	{
		Address before = hashMap.remove( ssn );
		return ( before == null );
	}
	
	//업데이트하는 코드 
	@Override
	public boolean update( int ssn, String name, String tel )
	{
		Address before = hashMap.put( ssn, new Address( name, tel ) );
		return ( before != null );
	}
	
	//검색하는 코드 
	@Override
	public Address search( int ssn )
	{
		if ( !hashMap.containsKey( ssn ) )
			return null;

		return hashMap.get( ssn );
	}
	
	//존재하는 파일을 읽어오는 코드 
	public void load( String filename ) throws IOException
	{
		BufferedReader in = new BufferedReader( new FileReader( filename ) );
		String line;
		
		while ( ( line = in.readLine() ) != null )
		{
			StringTokenizer st = new StringTokenizer( line );

			if ( ! st.hasMoreTokens() )
				continue;
			int key = Integer.parseInt( st.nextToken() );

			if ( ! st.hasMoreTokens() )
				continue;
			String name = st.nextToken();
			
			if ( ! st.hasMoreTokens() )
				continue;
			String tel = st.nextToken();
			
			hashMap.put( key, new Address( name, tel ) );
		}
	}

	//파일을 저장하는 코드 
	public void save( String filename ) throws IOException
	{
		BufferedWriter out = new BufferedWriter( new FileWriter( filename ) );
		
		for ( Entry<Integer, Address> entry : hashMap.entrySet() )
		{
			Integer key = entry.getKey();
			Address value = entry.getValue();
			
			out.write( key + " " + value );
			out.newLine();
		}
		
		out.close();
	}
	
	//HashMap을 clear하는 코드 
	public void clearHash() {
		hashMap.clear();
	}
}
