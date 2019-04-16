/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2019 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.bands.fill;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import net.sf.jasperreports.AbstractTest;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;

/** @author Tim Hewson */
public class BandFillTest extends AbstractTest
{

	@Override
	protected Map<String, Object> additionalReportParams()
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(JRParameter.REPORT_FILL_DETAIL_WITH_BLANK_ROWS, Boolean.TRUE);
		return params;
	}

	
	@Override
	protected JRDataSource createDataSource()
	{
		return new JRBeanArrayDataSource(
				IntStream.range(0, 90)
				.mapToObj(i -> new Ascii(33 + i, String.valueOf((char) (33 + i))))
				.toArray());
	}

	@Test(dataProvider = "testArgs")
	public void testReport(String jrxmlFileName, String referenceFileNamePrefix) 
			throws JRException, NoSuchAlgorithmException, IOException
	{
		runReport(jrxmlFileName, referenceFileNamePrefix);
	}

	@DataProvider
	public Object[][] testArgs()
	{
		return runReportArgs("net/sf/jasperreports/bands/fill/repo", "BandFillReport", 40);
	}

	public static class Ascii
	{
		long record_num;
		String record_val;

		Ascii(long number, String value) {
			this.record_num = number;
			this.record_val = value;
		}

		public long getRecord_num() {
			return record_num;
		}

		public String getRecord_val() {
			return record_val;
		}
	}
}