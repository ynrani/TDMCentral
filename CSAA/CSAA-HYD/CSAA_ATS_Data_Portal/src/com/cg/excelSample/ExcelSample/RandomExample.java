package com.cg.excelSample.ExcelSample;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RandomExample extends Random {/*
	EntityManager managerCsaaUser = openUserEntityManager();
	
	public static void main(String[] args) {
		RandomExample random = new RandomExample();
		System.out.println(random.getRandomValue("DICT_CITY"));
//		Integer maxVal = 10; // this will be select count(*) from table name;
//		System.out.println(random.nextInt(1, maxVal));
	}
	
	*//**
     * @param min generated value. Can't be > then max
     * @param max generated value
     * @return values in closed range [min, max].
     *//*
    public int nextInt(int min, int max) {
        //Assert.assertFalse("min can't be > then max; values:[" + min + ", " + max + "]", min > max);
        if (min == max) {
            return max;
        }

        return nextInt(max - min + 1) + min;
    }
    
    public String getRandomValue(String tableName) {
    	//TODO get count(*) from DB for given table name
		Query qry = managerCsaauser.createQuery("select count(*) from "+tableName);
		Integer maxRows = qry.getFirstResult();
		
    	//TODO get random value for random number
    	Integer rowNum = nextInt(1, maxRows);
    	// select * from " + tableName + " where ID = "+ rowNum ; 
    	
    	return null;
    }
*/}
