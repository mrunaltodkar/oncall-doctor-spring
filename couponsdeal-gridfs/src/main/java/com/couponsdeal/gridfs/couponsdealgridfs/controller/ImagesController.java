package com.couponsdeal.gridfs.couponsdealgridfs.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.couponsdeal.gridfs.couponsdealgridfs.entity.Coupon;
import com.couponsdeal.gridfs.couponsdealgridfs.entity.FileResource;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@RestController
public class ImagesController {

	@Autowired
	private GridFsOperations gridFsOperations;

	String fileID = "";

	@PostMapping("/savecoupons")
	public String saveCouponsManually() throws FileNotFoundException, IOException {

		DBObject metaData = new BasicDBObject();
		metaData.put("couponId", "2");
		metaData.put("couponName", "Clothing");
		metaData.put("couponDescription", "Set of Four shirts");
		metaData.put("couponPrice", "2000₹");

		InputStream inputStream = new FileInputStream("E:/couponsdeal-spring/images/clothing.jpg");// Coupon image file
																									// path
		metaData.put("type", "image");
		fileID = gridFsOperations.store(inputStream, "clothing.jpg", metaData).getId().toString();

		return "SUCCESSFULLY SAVED";

	}

	@GetMapping("/coupon/{couponId}")
	public ResponseEntity retrieveSingleImageFileMetaDataUsingCouponId(@PathVariable String couponId)
			throws FileNotFoundException, IOException {

		System.out.println("hitting");

		GridFSDBFile dbFile = gridFsOperations.findOne(new Query(Criteria.where("metadata.couponId").is(couponId)));
		DBObject dbObject = dbFile.getMetaData();
		InputStreamResource inputStreamResource = new InputStreamResource(dbFile.getInputStream());
		return new ResponseEntity(dbObject, HttpStatus.FOUND);

	}

	@GetMapping("/allcoupons")
	public ResponseEntity<List<FileResource>> retrieveAllCoupons() throws IOException, FileNotFoundException {

		List<GridFSDBFile> dbFileList = gridFsOperations.find(null);
		List<FileResource> fileResource = new ArrayList<>();

		for (GridFSDBFile dbFL : dbFileList) {
			FileResource fr = new FileResource(dbFL.getId().toString());
			fileResource.add(fr);
		}
		return new ResponseEntity<List<FileResource>>(fileResource, HttpStatus.OK);

	}

	@GetMapping("/retrieve/{id}")
	public ResponseEntity retrieveImageUsingObjectId(@PathVariable String id)
			throws FileNotFoundException, IOException {
		GridFSDBFile dbFile = gridFsOperations.findOne(new Query(Criteria.where("_id").is(id)));
		InputStreamResource inputStreamResource = new InputStreamResource(dbFile.getInputStream());
		return new ResponseEntity(inputStreamResource, HttpStatus.OK);

	}

	@PostMapping("/uploadcoupons")
	public ResponseEntity<Coupon> uploadCoupons(@RequestParam("filePath") MultipartFile file,
			@RequestBody Coupon coupon) throws IOException, FileNotFoundException {

		// http://localhost:8080/eportal/orders?id=1001
		// http://localhost:8080/upload?filePath="S:/couponsproj/images/clothing.jpg"

		if (!file.isEmpty()) {
			return new ResponseEntity<Coupon>(HttpStatus.NOT_ACCEPTABLE);
		}

		// define metadata
		DBObject metaData = new BasicDBObject();
		metaData.put("couponId", coupon.getCouponId());
		metaData.put("couponName", coupon.getCouponName());
		metaData.put("couponDescription", coupon.getCouponDescription());
		metaData.put("couponPrice", coupon.getCouponPrice());

		InputStream inputStream = file.getInputStream();
		metaData.put("type", "image");

		// store image file to mongodb
		fileID = gridFsOperations.store(inputStream, file.getOriginalFilename(), metaData).getId().toString();
		// return null;
		return new ResponseEntity<Coupon>(HttpStatus.CREATED);
	}

	/*
	 * @GetMapping("/allcouponsbackup") public ResponseEntity<List<InputStream>>
	 * retrieveAllCouponsBackUp() throws Exception {
	 * 
	 * List<GridFSDBFile> dbFileList = gridFsOperations.find(new Query());
	 * 
	 * // List<FileResource> fileResource = new ArrayList<>(); List<InputStream>
	 * fileResource = new ArrayList<>();
	 * 
	 * for (GridFSDBFile singleFile : dbFileList) { //
	 * System.out.println(singleFile);
	 * 
	 * System.out.println(singleFile.getInputStream());
	 * fileResource.add(singleFile.getInputStream());
	 * 
	 * } System.out.println(fileResource);
	 * 
	 * return new ResponseEntity<List<InputStream>>(fileResource, HttpStatus.FOUND);
	 * 
	 * }
	 * 
	 * @GetMapping("/save") public ResponseEntity<List<FileResource>>
	 * retrieveVideoFileForHomepage() throws IOException { List<GridFSDBFile>
	 * dbFileList = gridFsOperations.find(null); List<FileResource> fileResource =
	 * new ArrayList<>(); // List<InputStreamResource> inputStreamResources = new
	 * ArrayList<>(); // List<byte[]> byteFiles = new ArrayList<byte[]>(); int index
	 * = 0; for (GridFSDBFile dbFL : dbFileList) { DBObject metaData = new
	 * BasicDBObject(); metaData = dbFL.getMetaData(); FileResource fr = new
	 * FileResource(dbFL.getId().toString(), metaData.get("title").toString(),
	 * metaData.get("description").toString(), metaData.get("category").toString(),
	 * metaData.get("userName").toString()); fileResource.add(fr); //
	 * byteFiles.add(IOUtils.toByteArray(dbFL.getInputStream()));
	 * 
	 * // inputStreamResources.add(new InputStreamResource(dbFL.getInputStream()));
	 * } // for(int i=0;i<inputStreamResources.size();i++)
	 * 
	 * return new ResponseEntity<List<FileResource>>(fileResource, HttpStatus.OK); }
	 */
}
