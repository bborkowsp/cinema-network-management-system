export enum ProjectionTechnology {
  _2D = '2D',
  _3D = '3D',
  _4DX = '4DX',
}

export function getEnumKeyByValue(value: string): ProjectionTechnology | undefined {
  return Object.entries(ProjectionTechnology).find(([key, val]) => val === value)?.[0] as ProjectionTechnology | undefined;
}

export function getEnumValueByKey(key: string): ProjectionTechnology | undefined {
  return ProjectionTechnology[key as keyof typeof ProjectionTechnology];
}
