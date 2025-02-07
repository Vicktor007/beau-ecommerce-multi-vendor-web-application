package com.vic.ecommerce.service;

import com.vic.ecommerce.model.Home;
import com.vic.ecommerce.model.HomeCategory;

import java.util.List;

public interface HomeService {

    Home createHomePageData(List<HomeCategory> categories);

}
