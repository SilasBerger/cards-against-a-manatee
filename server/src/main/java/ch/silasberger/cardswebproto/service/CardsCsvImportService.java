package ch.silasberger.cardswebproto.service;

import ch.silasberger.cardswebproto.event.events.data.BlackCardCsv;
import ch.silasberger.cardswebproto.event.events.data.WhiteCardCsv;
import ch.silasberger.cardswebproto.util.ApplicationException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CardsCsvImportService {

    public List<BlackCardCsv> importBlackCardsCsv(File csv) throws ApplicationException {
        return importCardsCsv(BlackCardCsv.class, csv);
    }

    public List<WhiteCardCsv> importWhiteCardsCsv(File csv) throws ApplicationException {
        return importCardsCsv(WhiteCardCsv.class, csv);
    }

    private <T> List<T> importCardsCsv(Class<?> cardCsvType, File inputFile) throws ApplicationException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.typedSchemaFor(cardCsvType)
                .withoutHeader()
                .withColumnSeparator(',')
                .withoutComments();

        try {
            MappingIterator<T> cardsIter = csvMapper
                    .readerWithTypedSchemaFor(cardCsvType)
                    .with(csvSchema)
                    .readValues(inputFile);

            return cardsIter.readAll();
        } catch (IOException e) {
            throw new ApplicationException("Unable to load card set from path " + inputFile.getAbsolutePath(), e);
        }
    }

}
