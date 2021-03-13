package dev.wellington.beerstock.service;

import dev.wellington.beerstock.builder.BeerDTOBuilder;
import dev.wellington.beerstock.dto.BeerDTO;
import dev.wellington.beerstock.entity.Beer;
import dev.wellington.beerstock.exception.BeerAlreadyRegisteredException;
import dev.wellington.beerstock.exception.BeerNotFoundException;
import dev.wellington.beerstock.exception.BeerStockExceededException;
import dev.wellington.beerstock.mapper.BeerMapper;
import dev.wellington.beerstock.repository.BeerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

}
