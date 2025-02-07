package com.vic.ecommerce.service;

import com.vic.ecommerce.model.Seller;
import com.vic.ecommerce.model.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport( SellerReport sellerReport);

}
