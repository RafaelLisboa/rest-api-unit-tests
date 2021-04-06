package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.BeerDTOBuilder;
import one.digitalinnovation.beerstock.dto.BeerDTO;
import one.digitalinnovation.beerstock.entity.Beer;
import one.digitalinnovation.beerstock.exception.BeerAlreadyRegisteredException;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.exception.BeerStockExceededException;
import one.digitalinnovation.beerstock.mapper.BeerMapper;
import one.digitalinnovation.beerstock.repository.BeerRepository;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    private static final long INVALID_BEER_ID = 1L;

    @Mock
    private BeerRepository beerRepository;

    private final BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;


    @Test
    void whenBeerInformedThenItShouldBeCreated() throws BeerAlreadyRegisteredException {
        //given
        var beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        var beer = beerMapper.toModel(beerDTO);

        //when
        when(beerRepository.findByName(beer.getName())).thenReturn(Optional.empty());
        when(beerRepository.save(beer)).thenReturn(beer);

        //then
        BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

        assertThat(createdBeerDTO.getId(), is(equalTo(beer.getId())));
        assertThat(createdBeerDTO.getName(), is(equalTo(beer.getName())));
        assertThat(createdBeerDTO.getQuantity(), is(equalTo(beer.getQuantity())));
        assertThat(createdBeerDTO.getQuantity(), is(greaterThan(2)));
    }

    @Test
    void whenAlreadyRegisteredBeerInformedThenAnExceptionShouldBeThrow() throws BeerAlreadyRegisteredException {
        //given
        var expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        var duplicatedBeer = beerMapper.toModel(expectedBeerDTO);

        //when
        when(beerRepository.findByName(duplicatedBeer.getName())).thenReturn(Optional.of(duplicatedBeer));

        //then
        assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(expectedBeerDTO));

    }

    @Test
    void whenValidBeerNameIsGivenThenReturnABeer() throws BeerNotFoundException {
        //given
        var expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        var expectedBeer = beerMapper.toModel(expectedBeerDTO);

        //when
        when(beerRepository.findByName(expectedBeer.getName())).thenReturn(Optional.of(expectedBeer));

        //then
        BeerDTO foundBeerDTO = beerService.findByName(expectedBeer.getName());
        assertThat(foundBeerDTO, is(equalTo(expectedBeerDTO)));
    }

    @Test
    void whenNoRegisteredBeerNameIsGivenThenThrowsAnException() throws BeerNotFoundException {
        //given
        var expectedBeerDTO = BeerDTOBuilder.builder().build().toBeerDTO();

        //when
        when(beerRepository.findByName(expectedBeerDTO.getName())).thenReturn(Optional.empty());

        //then
        assertThrows(BeerNotFoundException.class, () -> beerService.findByName(expectedBeerDTO.getName()));
    }
}

