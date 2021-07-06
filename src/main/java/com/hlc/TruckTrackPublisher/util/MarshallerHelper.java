package com.hlc.TruckTrackPublisher.util;

import com.hlc.TruckTrackPublisher.domain.model.TmsMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.net.MalformedURLException;

@Slf4j
@Component

public class MarshallerHelper {
    public static String convertPoJoToXml(TmsMsg tmsMsg) throws JAXBException, SAXException, MalformedURLException {

        JAXBContext contextObj = JAXBContext.newInstance(TmsMsg.class);

        Marshaller marshallerObj = contextObj.createMarshaller();
        marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        Schema schema = schemaFactory.newSchema(new URL("http://www.w3.org/2001/XMLSchema"));


        StringWriter sw = new StringWriter();
        marshallerObj.marshal(tmsMsg, sw);

        return updateXmlTag(sw.toString());
    }

    private static String updateXmlTag(String tmsMsg) {
        return tmsMsg.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "<?xml version=\"1.0\"?>")
                .replace(" xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "");
    }
}
