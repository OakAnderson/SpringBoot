package br.com.erudio.converter;

import java.util.List;

import br.com.erudio.converter.mocks.MockBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;

public class DozerConverterBookTest {

    MockBook inputObject;

    @Before
    public void setUp() {
        inputObject = new MockBook();
    }

    @Test
    public void parseEntityToVOTest() {
        BookVO output = DozerConverter.parseObject(inputObject.mockEntity(), BookVO.class);
        Assert.assertEquals(Long.valueOf(0L), output.getKey());
        Assert.assertEquals("Title test0", output.getTitle());
        Assert.assertEquals("Author test0", output.getAuthor());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<BookVO> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), BookVO.class);

        BookVO outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getKey());
        Assert.assertEquals("Title test0", outputZero.getTitle());
        Assert.assertEquals("Author test0", outputZero.getAuthor());

        BookVO outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getKey());
        Assert.assertEquals("Title test7", outputSeven.getTitle());
        Assert.assertEquals("Author test7", outputSeven.getAuthor());

        BookVO outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        Assert.assertEquals("Title test12", outputTwelve.getTitle());
        Assert.assertEquals("Author test12", outputTwelve.getAuthor());
    }

    @Test
    public void parseVOToEntityTest() {
        Book output = DozerConverter.parseObject(inputObject.mockEntity(), Book.class);
        Assert.assertEquals(Long.valueOf(0L), output.getId());
        Assert.assertEquals("Title test0", output.getTitle());
        Assert.assertEquals("Author test0", output.getAuthor());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Book> outputList = DozerConverter.parseListObjects(inputObject.mockEntityList(), Book.class);

        Book outputZero = outputList.get(0);

        Assert.assertEquals(Long.valueOf(0L), outputZero.getId());
        Assert.assertEquals("Title test0", outputZero.getTitle());
        Assert.assertEquals("Author test0", outputZero.getAuthor());

        Book outputSeven = outputList.get(7);

        Assert.assertEquals(Long.valueOf(7L), outputSeven.getId());
        Assert.assertEquals("Title test7", outputSeven.getTitle());
        Assert.assertEquals("Author test7", outputSeven.getAuthor());

        Book outputTwelve = outputList.get(12);

        Assert.assertEquals(Long.valueOf(12L), outputTwelve.getId());
        Assert.assertEquals("Title test12", outputTwelve.getTitle());
        Assert.assertEquals("Author test12", outputTwelve.getAuthor());
    }
}