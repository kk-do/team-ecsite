package jp.co.internous.mushrooms.model.mapper;

import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.mushrooms.model.domain.dto.PurchaseHistoryDto;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface TblPurchaseHistoryMapper {
	long insert(Map<String, Object> parameter);
	

	List<PurchaseHistoryDto> findByUserId(@Param("userId")long userId);
	
	@Update("UPDATE tbl_purchase_history SET status = 0, updated_at = now() WHERE user_id = #{userId}" )
	long logicalDeleteByUserId(@Param("userId") long userId);


}