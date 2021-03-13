package dev.wellington.beerstock.service;

import dev.wellington.beerstock.builder.BeerDTOBuilder;
import dev.wellington.beerstock.dto.BeerDTO;
import dev.wellington.beerstock.entity.Beer;
import dev.wellington.beerstock.exception.BeerAlreadyRegisteredException;
import dev.wellington.beerstock.exception.BeerNotFoundException;
import dev.wellington.beerstock.exception.BeerStockExceededException;
import dev.wellington.beerstock.mapper.BeerMapper;
import dev.wellington.beerstock.repository.BeerRepository;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {
    private static final long INVALID_BEER_ID = 1L;

    @Mock
    private BeerRepository beerRepository;

    private BeerMapper beerMapper = BeerMapper.INSTANCE;

    @InjectMocks
    private BeerService beerService;

    @Test
    void whenBeerInformedThenItShoutBeCreated() throws BeerAlreadyRegisteredException {
        //given
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedBeer = beerMapper.toModel(beerDTO);

        //when
        Mockito.when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.empty());
        Mockito.when(beerRepository.save(expectedBeer)).thenReturn(expectedBeer);

        //then
        BeerDTO createdBeerDTO = beerService.createBeer(beerDTO);

        //Matcher of Hamcrest
        MatcherAssert.assertThat(createdBeerDTO.getId(), Matchers.is(Matchers.equalTo(beerDTO.getId())));
        MatcherAssert.assertThat(createdBeerDTO.getName(), Matchers.is(Matchers.equalTo(beerDTO.getName())));
        MatcherAssert.assertThat(createdBeerDTO.getQuantity(), Matchers.is(Matchers.equalTo(beerDTO.getQuantity())));

        //Matcher of Junit
        //        assertEquals(beerDTO.getId(), createdBeerDTO.getId());
        //        assertEquals(beerDTO.getName(), createdBeerDTO.getName());
    }
    @Test
    void whenAlreadyRegisteredInformedThenAnExeptionShouldBeThrown() throws BeerAlreadyRegisteredException {
        //given
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer duplicatedBeer = beerMapper.toModel(beerDTO);

        //when
        Mockito.when(beerRepository.findByName(beerDTO.getName())).thenReturn(Optional.of(duplicatedBeer));

        //then
        assertThrows(BeerAlreadyRegisteredException.class, () -> beerService.createBeer(beerDTO));
    }
    @Test
    void whenListBeerIsCalledThenReturnAListOfBeers() {
        //given
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedFoundBeer = beerMapper.toModel(beerDTO);

        //when
        Mockito.when(beerRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundBeer));

        //then
        List<BeerDTO> foundListBeersDTO = beerService.listAll();
        MatcherAssert.assertThat(foundListBeersDTO, is(not(empty())));
        MatcherAssert.assertThat(foundListBeersDTO.get(0), is(equalTo(beerDTO)));
    }

    @Test
    void whenListBeerIsCalledThenReturnAnEmptyListOfBeers() {
        //when
        Mockito.when(beerRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<BeerDTO> foundListBeersDTO = beerService.listAll();
        MatcherAssert.assertThat(foundListBeersDTO, is(empty()));
    }
    @Test
    void whenDeleteBeerIsCalledThenABeerShouldBeDelete() throws BeerNotFoundException {
        //given
        BeerDTO beerDTO = BeerDTOBuilder.builder().build().toBeerDTO();
        Beer expectedDeleteBeer = beerMapper.toModel(beerDTO);

        //when
        Mockito.when(beerRepository.findById(expectedDeleteBeer.getId())).thenReturn(Optional.of(expectedDeleteBeer));
        doNothing().when(beerRepository).deleteById(beerDTO.getId());

        //then
        beerService.deleteById(beerDTO.getId());

        verify(beerRepository, times(1)).findById(beerDTO.getId());
        verify(beerRepository, times(1)).deleteById(beerDTO.getId());
    }

}
