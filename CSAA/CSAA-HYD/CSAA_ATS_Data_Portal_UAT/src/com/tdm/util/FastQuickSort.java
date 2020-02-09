
package com.tdm.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/*
 * @(#)QSortAlgorithm.java	1.3   29 Feb 1996 James Gosling
 *
 * Copyright (c) 1994-1996 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this software
 * and its documentation for NON-COMMERCIAL or COMMERCIAL purposes and
 * without fee is hereby granted. 
 * Please refer to the file http://www.javasoft.com/copy_trademarks.html
 * for further important copyright and trademark information and to
 * http://www.javasoft.com/licensing.html for further important
 * licensing information for the Java (tm) Technology.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
 * CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
 * PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
 * NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
 * SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
 * SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
 * PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES").  SUN
 * SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
 * HIGH RISK ACTIVITIES.
 */



public class FastQuickSort 
{
	
	private Comparator m_comparator = null;
	
	public FastQuickSort(Comparator comparator)
	{
		m_comparator = comparator;
	}

	/** This is a generic version of C.A.R Hoare's Quick Sort 
	* algorithm.  This will handle arrays that are already
	* sorted, and arrays with duplicate keys.<BR>
	*
	* If you think of a one dimensional array as going from
	* the lowest index on the left to the highest index on the right
	* then the parameters to this function are lowest index or
	* left and highest index or right.  The first time you call
	* this function it will be with the parameters 0, a.length - 1.
	*
	* @param a	   an integer array
	* @param lo0	 left boundary of array partition
	* @param hi0	 right boundary of array partition
	*/
	
   private void quickSort(ArrayList colObjects, int l, int r)
   {
       if ( colObjects.size() == 0 || colObjects.size() == 1 )
       {
           return;
       }
		int M = 4;
		int i;
		int j;
		Object vObject;

			i = (r+l)/2;
			int comparisionValue = 0;
			if ( m_comparator != null )
			{
				comparisionValue = m_comparator.compare(colObjects.get(l), colObjects.get(i));
				if ( comparisionValue > 0 ) swap(colObjects,l,i); // Tri-Median Methode!

				comparisionValue = m_comparator.compare(colObjects.get(l), colObjects.get(r));
				if ( comparisionValue > 0 ) swap(colObjects,l,r); 

				comparisionValue = m_comparator.compare(colObjects.get(i),colObjects.get(r));
				if ( comparisionValue > 0 ) swap(colObjects,i,r);
			}
	
			j = r-1;
			swap(colObjects,i,j);
			i = l;
			
			vObject = colObjects.get(j);
			
			for(;;)
			{
				if ( m_comparator != null )
				{
					while (true)
					{
							comparisionValue = m_comparator.compare(colObjects.get(++i), vObject);
							if ( comparisionValue >= 0 || (i < 0 || (i+1) >= colObjects.size()) ) break; 
					}
					
					while (true)
					{
					    if ( (j-1) < 0 )
					    {
					        break;
					    }
						comparisionValue = m_comparator.compare(colObjects.get(--j), vObject);
						if ( comparisionValue <= 0 || ( j <= 0 || j >= colObjects.size()) ) break; 
					}
				}
				if (j<i) break;
				swap (colObjects,i,j);
	
			}
			swap(colObjects,i,r-1);
			if ( (j-l) > M ) quickSort(colObjects,l,j);
			if ( (r-i-1) > M ) quickSort(colObjects,i+1,r);
	}

	private void swap(ArrayList colObjects, int i, int j)
	{
		if ( i < 0 || i >= colObjects.size()) return;
		if ( j < 0 || j >= colObjects.size()) return;
		
		Object iObject = colObjects.get(i);
		Object jObject = colObjects.get(j);
		colObjects.remove(i);
		colObjects.add(i,jObject);
		colObjects.remove(j);
		colObjects.add(j,iObject);
	}

	
	private void insertionSort(ArrayList colObjects, int lo0, int hi0)
	{
       if ( colObjects.size() == 0 || colObjects.size() == 1 )
       {
           return;
       }
		int i;
		int j;
		Object vObject;

		for (i=(lo0+1);i<=hi0;i++)
		{
			vObject = colObjects.get(i);
			j=i;
			while ( (j > lo0) )
			{
				int comparisionValue = 0;
				if ( m_comparator != null )
				{
					comparisionValue = m_comparator.compare(colObjects.get(j-1), vObject);
					if ( comparisionValue <= 0 ) break;
				}
				colObjects.remove(j);
				colObjects.add(j, colObjects.get(j-1));
				j--;
			}
			colObjects.remove(j);
			colObjects.add(j, vObject);
			
		}
	}


	public Collection sort(Collection objects)
	{
		ArrayList alOutput = new ArrayList(objects);
		quickSort(alOutput, 0, objects.size() - 1);
		insertionSort(alOutput, 0, objects.size() - 1);
		return alOutput;
	}


}
