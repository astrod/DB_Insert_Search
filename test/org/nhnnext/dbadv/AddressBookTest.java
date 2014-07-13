package org.nhnnext.dbadv;

import java.io.IOException;
import java.util.Random;

public class AddressBookTest
{
	private final static String nameChar[] = {
		"건", "경", "글", "남", "다", "민", "병", "석", "성", "세",
		"소", "영", "우", "운", "원", "윤", "은", "종", "주", "진",
		"현", "효", "훈", "희"
	};

	private final static String familyName[] = {
		"김", "남", "도", "류", "민", "박", "양", "이", "전", "정", "주", "채", "최"
	};

	public static void main(String[] args) throws IOException
	{
		AddressBookUsingFile addressBook = new AddressBookUsingFile();
		Random random = new Random();
		
		int count = 0;
		long startTime = System.currentTimeMillis();
	
		//삽입 코드 
		for( int i = 0; i < 100000; i++ )
		{
			// 주민번호(int), 이름(string), 주소(string), 전화번호(string)
			int ssn = random.nextInt( 9000000 ) + 1000000;
			String name = familyName[ random.nextInt( familyName.length ) ] + nameChar[random.nextInt(nameChar.length)] + nameChar[random.nextInt(nameChar.length)];
			String tel = "010-" + ( random.nextInt(9000) + 1000 ) + "-" + ( random.nextInt(9000) + 1000 );
			
			boolean success = addressBook.insert( ssn, name, tel );
			if ( success )
				count++;
		}
		long stopTime = System.currentTimeMillis();
		
		addressBook.save( "address.txt" );
		System.out.println( "Random data count : " + count + ", time elapsed : " + (stopTime - startTime) + "ms" );

		
		AddressBookUsingFile addressBook2 = new AddressBookUsingFile();
		addressBook2.load( "address.txt" );
		
		count = 0;
		startTime = System.currentTimeMillis();
		addressBook2.clearHash();
		
		//검색 코드 
		for( int i = 0; i < 100000; i++ )
		{
			// 주민번호(int), 이름(string), 주소(string), 전화번호(string)
			int ssn = random.nextInt( 9000000 ) + 1000000;
			
			Address data = addressBook.search( ssn );

			if ( data != null ) {
				count++;
				addressBook2.insert(ssn, data.name, data.tel);
			}
		}
		stopTime = System.currentTimeMillis();

		addressBook2.save( "addresstest.txt" );
		System.out.println("Search count : " + count +", " + "Search data time elapsed : " + (stopTime - startTime) + "ms");
	}
}
