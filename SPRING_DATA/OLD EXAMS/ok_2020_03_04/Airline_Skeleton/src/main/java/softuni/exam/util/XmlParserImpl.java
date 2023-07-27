package softuni.exam.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class XmlParserImpl implements XmlParser {
    private JAXBContext jaxbContext;

    @Override
    public <T> T fromFile(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException {

        jaxbContext = JAXBContext.newInstance(tClass);

        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        return (T) unmarshaller.unmarshal(new FileReader(filePath));

    }
}
