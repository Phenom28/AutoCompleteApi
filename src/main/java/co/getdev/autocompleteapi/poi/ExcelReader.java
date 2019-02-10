package co.getdev.autocompleteapi.poi;

import co.getdev.autocompleteapi.domain.City;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.RETURN_BLANK_AS_NULL;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Ogundipe Segun David
 */
@Stateless
public class ExcelReader {
    
    private String sheet = "Sheet1";
    private int nameCellNum = 1;
    private int countryCodeCellNum = 8;
    private int timezoneCellNum = 17;
    private int latitudeCellNum = 4;
    private int longitudeCellNum = 5;
    private final String fileName = "excel/SuggestionDatabase.xlsx";
    ClassLoader classLoader = getClass().getClassLoader();
    
    private static final Logger LOGGER = Logger.getLogger(ExcelReader.class.getCanonicalName());
    
    public ExcelReader(){
        
    }
    
    public LinkedList<City> getAllCities(){
        LinkedList<City> cities = new LinkedList<>();
        City city;
        Workbook workbook;
        
        try (FileInputStream input = new FileInputStream(new File(classLoader.getResource(fileName).getFile()))){
            workbook = new XSSFWorkbook(input);
            Sheet citiesSheet = workbook.getSheet(getSheet());
            
            Iterator<Row> rowIterator = citiesSheet.iterator();
            while (rowIterator.hasNext()) {    
                city = new City();
                Row row = rowIterator.next();
                
                Cell nameCell = row.getCell(getNameCellNum(), RETURN_BLANK_AS_NULL);
                Cell countryCodeCell = row.getCell(getCountryCodeCellNum(), RETURN_BLANK_AS_NULL);
                Cell timeZoneCell = row.getCell(getTimezoneCellNum(), RETURN_BLANK_AS_NULL);
                Cell latitudeCell = row.getCell(getLatitudeCellNum(), RETURN_BLANK_AS_NULL);
                Cell longitudeCell = row.getCell(getLongitudeCellNum(), RETURN_BLANK_AS_NULL);
                
                city.setName(nameCell.getStringCellValue());
                city.setCountryCode(countryCodeCell.getStringCellValue());
                city.setTimezone(timeZoneCell.getStringCellValue());
                city.setLatitude(latitudeCell.getNumericCellValue());
                city.setLongitude(longitudeCell.getNumericCellValue());
                
                cities.add(city);
            }
            input.close();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return cities;
    }

    public String getSheet() {
        return sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = sheet;
    }

    public int getNameCellNum() {
        return nameCellNum;
    }

    public void setNameCellNum(int nameCellNum) {
        this.nameCellNum = nameCellNum;
    }

    public int getCountryCodeCellNum() {
        return countryCodeCellNum;
    }

    public void setCountryCodeCellNum(int countryCodeCellNum) {
        this.countryCodeCellNum = countryCodeCellNum;
    }

    public int getTimezoneCellNum() {
        return timezoneCellNum;
    }

    public void setTimezoneCellNum(int timezoneCellNum) {
        this.timezoneCellNum = timezoneCellNum;
    }

    public int getLatitudeCellNum() {
        return latitudeCellNum;
    }

    public void setLatitudeCellNum(int latitudeCellNum) {
        this.latitudeCellNum = latitudeCellNum;
    }

    public int getLongitudeCellNum() {
        return longitudeCellNum;
    }

    public void setLongitudeCellNum(int longitudeCellNum) {
        this.longitudeCellNum = longitudeCellNum;
    }
}
