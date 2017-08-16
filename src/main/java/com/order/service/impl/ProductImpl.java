package com.order.service.impl;

import com.order.config.ImageConfig;
import com.order.config.ProjectUrl;
import com.order.dataobject.ProductInfo;
import com.order.dto.CartDto;
import com.order.enums.ProductStatusEnum;
import com.order.enums.ResultEnum;
import com.order.exception.SellException;
import com.order.repository.ProductInfoRepository;
import com.order.service.ProductService;
import com.order.utils.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by 醒悟wjn on 2017/7/27.
 */
@Service
@Slf4j
public class ProductImpl implements ProductService {
    @Autowired
    ProjectUrl projectUrl;
    @Autowired
    ImageConfig imageConfig;
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findone(String id) {
        return repository.findOne(id);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
//保存商品
    @Override
    public ProductInfo save(ProductInfo productInfo,MultipartFile dealImageFile) {
        if(dealImageFile!=null&& dealImageFile.getSize()>0){
            String url=ImageUtil.getDetailImageSourceFileAbsolutePath(imageConfig.getDealSourceBasePath(),productInfo.getProductId());
            log.info("【商品图片路径】url={}",url);
            File sourceFileDir = new File(url).getParentFile();
            if(!sourceFileDir.exists()){
                try{
                 sourceFileDir.mkdirs();
                 log.info("【商品路径不存在】，创建路径{}",sourceFileDir.getPath());
                }
                 catch(Exception e){
                    log.info("【上传图片】，文件路径不存在，sourceFileDir={}",sourceFileDir);
                 }
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream=new FileOutputStream(url);
                fileOutputStream.write(dealImageFile.getBytes());
                fileOutputStream.flush();
                log.info("【上传商品图片】，productid={}",productInfo.getProductId());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try{
                fileOutputStream.close();}
                catch(IOException e){
                    e.printStackTrace();
                }
            }
            String a=ImageUtil.getDetailImageSourceFileRelativePath(productInfo.getProductId());
            productInfo.setProductIcon(projectUrl.getImageUrl()+a);
        }
        return repository.save(productInfo);
    }
    /*取消订单后加库存*/
    @Transactional
    @Override
    public void increateStock(List<CartDto> cartDTOList) {
        for(CartDto cartDto:cartDTOList){
            ProductInfo productInfo=repository.findOne(cartDto.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int a=productInfo.getProductStock()+cartDto.getProductQuantity();
            productInfo.setProductStock(a);
            repository.save(productInfo);
        }
    }
     //减库存
    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDTOList) {
      for(CartDto cartDto:cartDTOList){
          ProductInfo productInfo=repository.findOne(cartDto.getProductId());
          if(productInfo==null){
              throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
          }
          int a=productInfo.getProductStock()-cartDto.getProductQuantity();
          if(a<0){
              throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
          }
          productInfo.setProductStock(a);
          repository.save(productInfo);
      }
    }
//上架
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo=repository.findOne(productId);
        if(productInfo.getProductStatus()==ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_UP_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        repository.save(productInfo);
        return productInfo;
    }
//下架
    @Override
    public ProductInfo down(String productId) {
        ProductInfo productInfo=repository.findOne(productId);
        if(productInfo.getProductStatus()!=ProductStatusEnum.UP.getCode()){
            throw new SellException(ResultEnum.PRODUCT_DOWN_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        repository.save(productInfo);
        return productInfo;
    }
}
