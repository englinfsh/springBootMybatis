package com.lin.feng.sheng.dao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.lin.feng.sheng.entity.Teacher;

public interface TeacherMapper {


	@Insert("insert into teacher(id,name,age) values('${t.id}','${t.name}','${t.age}')")
	public boolean saveTeacher(@Param("t")Teacher t);

	 @Select("SELECT * FROM Teacher WHERE id = #{id}")
	 Teacher findById(@Param("id") int id);

	// This example creates a prepared statement, something like select * from teacher where name = ?;
	 @Select("Select * from teacher where name = #{name}")
	 Teacher selectTeachForGivenName1(@Param("name") String name);
	 // This example creates n inlined statement, something like select * from teacher where name = 'someName';
	 @Select("Select * from teacher where name = '${name}'")
	 Teacher selectTeachForGivenName2(@Param("name") String name);


	 @Update("update teacher set status='${status}'  where id = #{id} and status = '${oldStatus}' ")
	 int  updateTh(@Param("id") int id, @Param("status") String status,@Param("oldStatus") String oldStatus);

}
