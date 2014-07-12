package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.ClassRoomBiz;
import net.cedu.biz.examination.ExamRoomBiz;
import net.cedu.biz.examination.ExamroomPlanBiz;
import net.cedu.dao.examination.ExamroomPlanDao;
import net.cedu.entity.examination.ClassRoom;
import net.cedu.entity.examination.ExamRoom;
import net.cedu.entity.examination.ExamroomPlan;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamroomPlanBizImpl implements ExamroomPlanBiz {
	@Autowired
	private ExamroomPlanDao examrooplandao;
	@Autowired
	private ExamRoomBiz examroombiz;
	@Autowired
	private ClassRoomBiz classroombiz;
	

	public boolean createNew(ExamroomPlan examroomplan) throws Exception {
		// TODO Auto-generated method stub
		 Object o=examrooplandao.save(examroomplan);
		 if(o!=null){return true;}
		 return false;
		
	}

	public Object deleteExamroomPlanById(int id) throws Exception {
		// TODO Auto-generated method stub
		return examrooplandao.deleteById(id);
	}


	public ExamroomPlan findByExamroomPlanId(int id) throws Exception {
		// TODO Auto-generated method stub
		return examrooplandao.findById(id);
	}

	public int findExamroomPlanPageCount(ExamroomPlan examroomplan,
			PageResult<ExamroomPlan> pr) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ExamroomPlan> page(PageResult<ExamroomPlan> result)
			throws Exception {
		List<ExamroomPlan> examroomplanlist = new ArrayList<ExamroomPlan>();
		ExamroomPlan d=null;
		PageParame pp = new PageParame(result);
		Long[] list = examrooplandao.getIDs(pp);
		 for(Long id : list){
		    	if (null != (d = examrooplandao.findById(Integer.parseInt(id
						.toString())))) {
		    		if(d.getExamRoomId()!=null &&d.getExamRoomId()>0){
					ExamRoom examroom = examroombiz.findByExamroomId(d.getExamRoomId());
					if (examroom != null) {
						d.setExamRoomname(examroom.getName());
					}
		    		}
		    		if(d.getClassroomId()!=null && d.getClassroomId()>0){
					ClassRoom classroom = classroombiz.findClassroomById((d.getClassroomId()));
					if (classroom != null){
						d.setClassroomname(classroom.getName());
					}
		    		}
					examroomplanlist.add(d);
				}
		 }
		return examroomplanlist;
	}
	public int findAllexamRoomplanCount(PageResult<ExamroomPlan> pr) throws Exception {
		PageParame p = new PageParame(pr);
		
		return examrooplandao.getCounts(p);
	}

}
